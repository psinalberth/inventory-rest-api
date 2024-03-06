package com.github.psinalberth.domain.inventory.application.port.incoming;

import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface RegisterInventoryItemUseCase {

    InventoryItemDto register(RegisterInventoryItemCommand command);

    @Getter
    @Setter
    class RegisterInventoryItemCommand {

        @NotEmpty(message = "Product is required.")
        String productId;

        @NotNull(message = "Quantity is required.")
        BigDecimal quantity;

        @NotNull(message = "Batch type is required.")
        Long batchTypeId;

        @Schema(hidden = true)
        Long inventoryId;
    }
}
