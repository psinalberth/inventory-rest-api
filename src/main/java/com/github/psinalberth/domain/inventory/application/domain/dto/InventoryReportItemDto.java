package com.github.psinalberth.domain.inventory.application.domain.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;

public record InventoryReportItemDto(

        @CsvBindByPosition(position = 0)
        @CsvBindByName(column = "cod_produto")
        String productId,

        @CsvBindByPosition(position = 1)
        @CsvBindByName(column = "nome_produto")
        String name,

        @CsvBindByPosition(position = 2)
        @CsvBindByName(column = "preco")
        BigDecimal price,

        @CsvBindByPosition(position = 3)
        @CsvBindByName(column = "qte_cadastrada")
        BigDecimal expectedQuantity,

        @CsvBindByPosition(position = 4)
        @CsvBindByName(column = "valor_total_cadastrado")
        BigDecimal expectedTotalPrice,

        @CsvBindByPosition(position = 5)
        @CsvBindByName(column = "qte_real")
        BigDecimal actualQuantity,

        @CsvBindByPosition(position = 6)
        @CsvBindByName(column = "valor_total_real")
        BigDecimal actualTotalPrice

) {

}
