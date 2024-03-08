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

        @Schema(example = "78912345678901")
        @NotEmpty(message = "Product is required.")
        String productId;

        @Schema(example = "10")
        @NotNull(message = "Quantity is required.")
        BigDecimal quantity;

        @Schema(example = "1")
        @NotNull(message = "Batch type is required.")
        Long batchTypeId;

        @Schema(hidden = true)
        Long inventoryId;
    }
}
