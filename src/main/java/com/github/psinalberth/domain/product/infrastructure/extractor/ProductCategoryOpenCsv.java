package com.github.psinalberth.domain.product.infrastructure.extractor;

import com.github.psinalberth.domain.product.core.model.IProductCategory;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ProductCategoryOpenCsv implements IProductCategory {

        @CsvBindByName(column = "cod_categoria")
        private Long code;

        @CsvIgnore
        @CsvBindByName(column = "desc_categoria")
        private String name;
}
