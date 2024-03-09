package com.github.psinalberth.domain.inventory.adapters.web;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryReportItemDto;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryItemsUseCase;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryUseCase;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryUseCase.QueryInventoryCommand;
import com.github.psinalberth.domain.inventory.application.port.incoming.RegisterInventoryItemUseCase;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryItemsUseCase.QueryInventoryItemsCommand;
import static com.github.psinalberth.domain.inventory.application.port.incoming.RegisterInventoryItemUseCase.RegisterInventoryItemCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryItemRegistryController implements InventoryItemRegistryControllerOpenApi {

    private final QueryInventoryUseCase queryInventoryUseCase;
    private final RegisterInventoryItemUseCase registerInventoryItemUseCase;
    private final QueryInventoryItemsUseCase queryInventoryItemsUseCase;

    @Override
    @PostMapping(value = "{code}/items")
    public ResponseEntity<InventoryItemDto> register(@PathVariable String code, @Valid @RequestBody RegisterInventoryItemCommand command) {
        return Optional.ofNullable(queryInventoryUseCase.query(new QueryInventoryCommand(code)))
                .map(inventory -> {
                    command.setInventoryId(inventory.getInventoryId());
                    return command;
                })
                .map(registerInventoryItemUseCase::register)
                .map(result -> ResponseEntity.status(HttpStatus.CREATED).body(result))
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    @GetMapping(value = "{code}/items")
    public ResponseEntity<List<InventoryItemDto>> query(@PathVariable String code) {
        return ResponseEntity.ok(queryInventoryItemsUseCase.query(new QueryInventoryItemsCommand(code)));
    }

    @GetMapping(value = "{code}/report-items")
    public ResponseEntity<StreamingResponseBody> queryReport(@PathVariable String code) {

        var items = queryInventoryItemsUseCase.queryReport(new QueryInventoryItemsCommand(code));

        StreamingResponseBody responseBody = outputStream -> {
            try (var writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                new StatefulBeanToCsvBuilder<InventoryReportItemDto>(writer)
                        .withQuotechar('\'')
                        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                        .build()
                        .write(items);
            } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        };

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", "report.csv"))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .body(responseBody);
    }
}