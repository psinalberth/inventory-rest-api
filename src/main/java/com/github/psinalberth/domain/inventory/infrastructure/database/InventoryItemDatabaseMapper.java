package com.github.psinalberth.domain.inventory.infrastructure.database;

import com.github.psinalberth.domain.inventory.core.model.InventoryItem;
import com.github.psinalberth.domain.inventory.core.model.InventoryItemReport;
import com.github.psinalberth.domain.product.infrastructure.database.InventoryItemReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface InventoryItemDatabaseMapper {

    @Mapping(target = "id", expression = "java(toInventoryItemId(inventoryId, productId, batchTypeId))")
    InventoryItemMongoEntity toEntity(final InventoryItem item);

    default InventoryItemId toInventoryItemId(String inventoryId, String productId, String batchTypeId) {
        return new InventoryItemId(inventoryId, productId, batchTypeId);
    }

    InventoryItem toDomain(final InventoryItemMongoEntity entity);

    InventoryItemReport toDomainReport(final InventoryItemReportEntity entity);
}
