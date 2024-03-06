package com.github.psinalberth.domain.product.adapters.mapper;

import com.github.psinalberth.domain.product.application.domain.model.IProductGroup;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class ProductGroupOpenCsvDto implements IProductGroup {

    @CsvBindByName(column = "cod_filial")
    private Long groupId;

    @CsvBindByName(column = "desc_filial")
    private String name;
}
