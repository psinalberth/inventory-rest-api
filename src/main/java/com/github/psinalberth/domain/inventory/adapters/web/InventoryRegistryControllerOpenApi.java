package com.github.psinalberth.domain.inventory.adapters.web;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryDto;
import com.github.psinalberth.domain.inventory.application.port.incoming.CreateInventoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Inventory", description = "Manage inventory")
public interface InventoryRegistryControllerOpenApi {

    @Operation(summary = "Retrieve an inventory by access code")
    ResponseEntity<InventoryDto> findByCode(String code);

    @Operation(summary = "Create a new inventory")
    ResponseEntity<InventoryDto> create(CreateInventoryUseCase.CreateInventoryCommand command);

    @Operation(summary = "Upload base product related to a inventory")
    ResponseEntity<Void> upload(String code, MultipartFile file) throws IOException;
}
