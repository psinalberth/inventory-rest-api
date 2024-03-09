package com.github.psinalberth.domain.product.application.domain.port.incoming;

import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public interface CreateProductUseCase {

    ProductDto create(CreateProductCommand command);


        record CreateProductCommand(
                @NotEmpty(message = "Barcode is required.")
                String barCode,
                @NotEmpty(message = "Name is required.")
                String name,
                String category,
                String department,
                String group,
                BigDecimal price,

                BigDecimal quantity
        ) {

    }
}
