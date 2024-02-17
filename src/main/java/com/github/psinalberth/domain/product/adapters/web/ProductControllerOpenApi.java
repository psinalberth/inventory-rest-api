package com.github.psinalberth.domain.product.adapters.web;

import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import com.github.psinalberth.domain.product.application.domain.port.incoming.CreateProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "Product", description = "Manages products")
public interface ProductControllerOpenApi {

    @Operation(summary = "Creates a new product")
    ResponseEntity<ProductDto> create(@Valid @RequestBody CreateProductUseCase.CreateProductCommand command);

    @Operation(summary = "Retrieves product from database")
    ResponseEntity<ProductDto> findById(String productId);
}
