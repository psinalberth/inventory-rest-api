package com.github.psinalberth.domain.product.infrastructure.extractor;

import com.github.psinalberth.domain.product.core.model.IProductDepartment;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class ProductDepartmentOpenCsv implements IProductDepartment {

        @CsvBindByName(column = "cod_departamento")
        private Long code;

        @CsvBindByName(column = "desc_departamento")
        private String name;
}
