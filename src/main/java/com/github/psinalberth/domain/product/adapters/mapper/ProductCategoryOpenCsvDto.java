package com.github.psinalberth.domain.product.adapters.mapper;

import com.github.psinalberth.domain.product.application.domain.model.IProductCategory;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ProductCategoryOpenCsvDto implements IProductCategory {

    @CsvBindByName(column = "cod_categoria")
    private Long categoryId;

    @CsvBindByName(column = "desc_categoria")
    private String name;
}
