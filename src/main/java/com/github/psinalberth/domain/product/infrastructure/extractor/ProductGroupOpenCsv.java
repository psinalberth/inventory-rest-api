package com.github.psinalberth.domain.product.infrastructure.extractor;

import com.github.psinalberth.domain.product.core.model.IProductGroup;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ProductGroupOpenCsv implements IProductGroup {

        @CsvBindByName(column = "cod_filial")
        private Long code;

        @CsvBindByName(column = "desc_filial")
        private String name;
}
