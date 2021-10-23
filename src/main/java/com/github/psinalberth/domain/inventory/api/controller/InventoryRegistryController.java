package com.github.psinalberth.domain.inventory.api.controller;

import com.github.psinalberth.domain.inventory.dto.output.InventoryDto;
import com.github.psinalberth.domain.inventory.usecase.CreateInventoryUseCase;
import com.github.psinalberth.domain.inventory.usecase.QueryInventoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static com.github.psinalberth.domain.inventory.usecase.QueryInventoryUseCase.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryRegistryController {

    private final CreateInventoryUseCase createInventoryUseCase;
    private final QueryInventoryUseCase queryInventoryUseCase;

    @GetMapping(value = "/{code}")
    public ResponseEntity<InventoryDto> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(queryInventoryUseCase.query(new QueryInventoryCommand(code)));
    }

    @PostMapping
    public ResponseEntity<InventoryDto> create(@Valid @RequestBody CreateInventoryUseCase.CreateInventoryCommand command) {
        return Optional.ofNullable(createInventoryUseCase.create(command))
                .map(inventory -> ResponseEntity.created(buildURI(inventory)).body(inventory))
                .orElseThrow(() -> new RuntimeException("Error creating inventory audit."));
    }

    private URI buildURI(InventoryDto inventory) {
        return MvcUriComponentsBuilder.fromMethodName(InventoryRegistryController.class, "findByCode", inventory.getAccessCode())
                .build()
                .toUri();
    }
}