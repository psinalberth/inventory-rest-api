package com.github.psinalberth.domain.inventory.api.controller;

import com.github.psinalberth.domain.inventory.dto.output.InventoryItemDto;
import com.github.psinalberth.domain.inventory.usecase.QueryInventoryUseCase;
import com.github.psinalberth.domain.inventory.usecase.QueryInventoryItemsUseCase;
import com.github.psinalberth.domain.inventory.usecase.QueryInventoryUseCase.QueryInventoryCommand;
import com.github.psinalberth.domain.inventory.usecase.RegisterInventoryItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static com.github.psinalberth.domain.inventory.usecase.QueryInventoryItemsUseCase.*;
import static com.github.psinalberth.domain.inventory.usecase.RegisterInventoryItemUseCase.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/inventory/{code}/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryItemRegistryController {

    private final QueryInventoryUseCase queryInventoryUseCase;
    private final RegisterInventoryItemUseCase registerInventoryItemUseCase;
    private final QueryInventoryItemsUseCase queryInventoryItemsUseCase;

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

    @GetMapping
    public ResponseEntity<List<InventoryItemDto>> query(@PathVariable String code) {
        return ResponseEntity.ok(queryInventoryItemsUseCase.query(new QueryInventoryItemsCommand(code)));
    }
}