package com.github.psinalberth.domain.product.adapters.mapper;

import com.github.psinalberth.domain.product.application.domain.model.IProduct;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductOpenCsvDto implements IProduct {

    @CsvBindByName(column = "cod_barras")
    private String productId;

    @CsvBindByName(column = "desc_produto")
    private String name;

    @CsvRecurse
    private ProductCategoryOpenCsvDto category;

    @CsvRecurse
    private ProductGroupOpenCsvDto group;

    @CsvRecurse
    private ProductDepartmentOpenCsvDto department;

    @CsvBindByName(column = "qte")
    private BigDecimal quantity;

    @CsvBindByName(column = "valor")
    private BigDecimal price;
}
