package com.github.psinalberth.domain.product.adapters.web;

import com.github.psinalberth.domain.inventory.adapters.web.InventoryRegistryController;
import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import com.github.psinalberth.domain.product.application.domain.port.incoming.CreateProductUseCase;
import com.github.psinalberth.domain.product.application.domain.port.incoming.QueryProductUseCase;
import com.github.psinalberth.domain.product.application.domain.port.incoming.QueryProductUseCase.QueryProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController implements ProductControllerOpenApi {

    private final CreateProductUseCase createProductUseCase;
    private final QueryProductUseCase queryProductUseCase;

    @Override
    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody CreateProductUseCase.CreateProductCommand command) {
        return Optional.ofNullable(createProductUseCase.create(command))
                .map(product -> ResponseEntity.created(buildURI(product)).body(product))
                .orElseThrow(() -> new RuntimeException("Error creating product."));
    }

    @Override
    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable String productId) {
        return ResponseEntity.ok(queryProductUseCase.query(new QueryProductCommand(productId)));
    }

    private URI buildURI(ProductDto product) {
        return  MvcUriComponentsBuilder.fromMethodName(InventoryRegistryController.class, "findById", product.getProductId())
                .build()
                .toUri();
    }
}
