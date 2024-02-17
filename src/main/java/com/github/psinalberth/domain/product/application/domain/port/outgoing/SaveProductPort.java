package com.github.psinalberth.domain.product.application.domain.port.outgoing;

import com.github.psinalberth.domain.product.application.domain.model.Product;

public interface SaveProductPort {

    Product save(Product product);
}
