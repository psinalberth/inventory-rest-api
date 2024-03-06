package com.github.psinalberth.domain.product.adapters.mapper;

import com.github.psinalberth.domain.product.application.domain.model.IProductDepartment;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ProductDepartmentOpenCsvDto implements IProductDepartment {

    @CsvBindByName(column = "cod_departamento")
    private Long departmentId;

    @CsvBindByName(column = "desc_departamento")
    private String name;
}
