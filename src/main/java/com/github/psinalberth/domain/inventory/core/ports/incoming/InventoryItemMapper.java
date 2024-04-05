package com.github.psinalberth.domain.inventory.core.ports.incoming;

import com.github.psinalberth.domain.inventory.core.model.InventoryItem;
import com.github.psinalberth.domain.inventory.core.model.command.RegisterInventoryItemCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jsr330")
public interface InventoryItemMapper {

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    InventoryItem toInventoryItem(final RegisterInventoryItemCommand command);
}
