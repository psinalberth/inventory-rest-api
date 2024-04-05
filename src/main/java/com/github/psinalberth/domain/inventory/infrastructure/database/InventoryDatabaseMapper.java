package com.github.psinalberth.domain.inventory.infrastructure.database;

import com.github.psinalberth.domain.inventory.core.model.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface InventoryDatabaseMapper {

    InventoryMongoEntity toEntity(final Inventory inventory);

    Inventory toDomain(final InventoryMongoEntity entity);
}
