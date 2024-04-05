package com.github.psinalberth.domain.product.infrastructure.extractor;

import com.github.psinalberth.domain.product.core.model.Product;
import com.github.psinalberth.domain.shared.port.outgoing.DataImporter;

public interface ProductDataImporter extends DataImporter<Product, ProductOpenCsv> {

}
