package com.github.psinalberth.domain.product.application.domain.port.incoming;

import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import lombok.Value;

import jakarta.validation.constraints.NotEmpty;

public interface CreateProductUseCase {

    ProductDto create(CreateProductCommand command);


        record CreateProductCommand(
                @NotEmpty(message = "Barcode is required.")
                String barCode,
                @NotEmpty(message = "Name is required.")
                String name,
                String category,
                String department,
                String group
        ) {

    }
}
