package com.github.psinalberth.domain.inventory.adapters.web;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryDto;
import com.github.psinalberth.domain.inventory.application.port.incoming.CreateInventoryUseCase;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryUseCase;
import com.github.psinalberth.domain.product.application.domain.port.incoming.ImportProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import jakarta.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryUseCase.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryRegistryController implements InventoryRegistryControllerOpenApi {

    private final CreateInventoryUseCase createInventoryUseCase;
    private final QueryInventoryUseCase queryInventoryUseCase;
    private final ImportProductUseCase importProductUseCase;

    @Override
    @GetMapping(value = "/{code}")
    public ResponseEntity<InventoryDto> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(queryInventoryUseCase.query(new QueryInventoryCommand(code)));
    }

    @Override
    @PostMapping
    public ResponseEntity<InventoryDto> create(@Valid @RequestBody CreateInventoryUseCase.CreateInventoryCommand command) {
        return Optional.ofNullable(createInventoryUseCase.create(command))
                .map(inventory -> ResponseEntity.created(buildURI(inventory)).body(inventory))
                .orElseThrow(() -> new RuntimeException("Error creating inventory audit."));
    }

    @Override
    @PutMapping(value = "/{code}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upload(@PathVariable String code, @RequestPart(value = "file") MultipartFile file) throws IOException {
        var command = new ImportProductUseCase.ImportProductCommand(code, file.getInputStream());
        importProductUseCase.doImport(command);
        return ResponseEntity.noContent().build();
    }

    private URI buildURI(InventoryDto inventory) {
        return MvcUriComponentsBuilder.fromMethodName(InventoryRegistryController.class, "findByCode", inventory.getAccessCode())
                .build()
                .toUri();
    }
}