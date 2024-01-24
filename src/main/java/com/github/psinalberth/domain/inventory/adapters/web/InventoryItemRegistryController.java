package com.github.psinalberth.domain.inventory.adapters.web;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryUseCase;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryItemsUseCase;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryUseCase.QueryInventoryCommand;
import com.github.psinalberth.domain.inventory.application.port.incoming.RegisterInventoryItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryItemsUseCase.*;
import static com.github.psinalberth.domain.inventory.application.port.incoming.RegisterInventoryItemUseCase.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/inventory/{code}/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryItemRegistryController implements InventoryItemRegistryControllerOpenApi {

    private final QueryInventoryUseCase queryInventoryUseCase;
    private final RegisterInventoryItemUseCase registerInventoryItemUseCase;
    private final QueryInventoryItemsUseCase queryInventoryItemsUseCase;

    @Override
    @PostMapping
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
    @GetMapping
    public ResponseEntity<List<InventoryItemDto>> query(@PathVariable String code) {
        return ResponseEntity.ok(queryInventoryItemsUseCase.query(new QueryInventoryItemsCommand(code)));
    }
}