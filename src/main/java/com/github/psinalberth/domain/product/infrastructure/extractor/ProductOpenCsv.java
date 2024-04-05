package com.github.psinalberth.domain.product.infrastructure.extractor;

import com.github.psinalberth.domain.product.core.model.IProduct;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(fluent = true)
public class ProductOpenCsv implements IProduct {

        @CsvBindByName(column = "cod_barras")
        private String id;

        @CsvBindByName(column = "desc_produto")
        private String name;

        @CsvRecurse
        private ProductCategoryOpenCsv category;

        @CsvRecurse
        private ProductDepartmentOpenCsv department;

        @CsvRecurse
        private ProductGroupOpenCsv group;

        @CsvBindByName(column = "qte")
        private BigDecimal quantity;

        @CsvBindByName(column = "valor")
        private BigDecimal price;
}
