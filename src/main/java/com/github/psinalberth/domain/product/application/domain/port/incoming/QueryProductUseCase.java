package com.github.psinalberth.domain.product.application.domain.port.incoming;

import com.github.psinalberth.domain.product.application.domain.dto.ProductDto;
import lombok.Value;

public interface QueryProductUseCase {

    ProductDto query(QueryProductCommand command);

    @Value
    class QueryProductCommand {

        String productId;
    }
}
