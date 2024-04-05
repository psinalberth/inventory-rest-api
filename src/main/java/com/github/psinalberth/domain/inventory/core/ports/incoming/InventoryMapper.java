package com.github.psinalberth.domain.inventory.core.ports.incoming;

import com.github.psinalberth.domain.inventory.core.model.BatchType;
import com.github.psinalberth.domain.inventory.core.model.Inventory;
import com.github.psinalberth.domain.inventory.core.model.command.CreateInventoryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface InventoryMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Inventory toInventory(final CreateInventoryCommand command);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "name", source = "name")
    BatchType toBatchType(final String name);
}
