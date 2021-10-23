package com.github.psinalberth.domain.inventory.usecase;

import com.github.psinalberth.domain.inventory.dto.output.InventoryItemDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
