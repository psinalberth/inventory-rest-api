package com.github.psinalberth.domain.inventory.infrastructure.extractor;

import com.github.psinalberth.api.providers.opencsv.CsvExporter;
import com.github.psinalberth.domain.inventory.core.model.InventoryItemReport;
import jakarta.inject.Named;

import java.util.Optional;

@Named
class InventoryItemOpenCsvExporter extends CsvExporter<InventoryItemOpenCsv, InventoryItemReport> implements InventoryItemDataExporter {
    @Override
    public Class<InventoryItemOpenCsv> getType() {
        return InventoryItemOpenCsv.class;
    }

    @Override
    public InventoryItemOpenCsv mapFromBean(final InventoryItemReport input) {
        return Optional.ofNullable(input)
                .map(value -> new InventoryItemOpenCsv(
                       value.productId(),
                        value.name(),
                        value.price(),
                        value.expectedQuantity(),
                        value.expectedTotalPrice(),
                        value.actualQuantity(),
                        value.actualTotalPrice()
                ))
                .orElse(null);
    }

    @Override
    public String[] getHeaders() {
        return new String[] {
                "cod_produto",
                "nome_produto",
                "preco",
                "qte_cadastrada",
                "valor_total_cadastrado",
                "qte_real",
                "valor_total_real"
        };
    }
}
