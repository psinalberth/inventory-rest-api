package com.github.psinalberth.domain.inventory.adapters.web;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import com.github.psinalberth.domain.inventory.application.port.incoming.RegisterInventoryItemUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Inventory Items", description = "Manage items")
public interface InventoryItemRegistryControllerOpenApi {

    @Operation(summary = "Register an inventory item")
    ResponseEntity<InventoryItemDto> register(String code, RegisterInventoryItemUseCase.RegisterInventoryItemCommand command);

    @Operation(summary = "Query inventory items by inventory access code")
    ResponseEntity<List<InventoryItemDto>> query(String code);
}
