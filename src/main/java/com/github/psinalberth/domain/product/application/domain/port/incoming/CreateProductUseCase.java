package com.github.psinalberth.domain.product.application.domain.port.incoming;

import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface CreateProductUseCase {

    ProductDto create(CreateProductCommand command);

    @Value
    class CreateProductCommand {

        @NotEmpty(message = "Barcode is required.")
        String barCode;

        @NotEmpty(message = "Name is required.")
        String name;

        List<@NotEmpty(message = "Category is required.") String> categories;

        List<@NotEmpty(message = "Group is required.") String> groups;
    }
}
