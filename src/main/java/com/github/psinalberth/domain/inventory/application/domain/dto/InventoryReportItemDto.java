package com.github.psinalberth.domain.inventory.application.domain.dto;

import com.opencsv.bean.CsvBindByName;

import java.math.BigDecimal;

public record InventoryReportItemDto(

        @CsvBindByName(column = "cod_produto")
        String productId,

        @CsvBindByName(column = "nome_produto")
        String name,

        @CsvBindByName(column = "preco")
        BigDecimal price,

        @CsvBindByName(column = "qte_cadatrada")
        BigDecimal expectedQuantity,

        @CsvBindByName(column = "valor_total_cadastrado")
        BigDecimal actualQuantity,

        @CsvBindByName(column = "qte_real")
        BigDecimal expectedTotalPrice,

        @CsvBindByName(column = "valor_total_real")
        BigDecimal actualTotalPrice

) {

}
