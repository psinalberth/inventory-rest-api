package com.github.psinalberth.domain.inventory.mapper;

import com.github.psinalberth.domain.inventory.model.InventoryItem;
import com.github.psinalberth.domain.inventory.dto.output.InventoryItemDto;
import com.github.psinalberth.domain.inventory.usecase.RegisterInventoryItemUseCase;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        InventoryMapper.class
})
public interface InventoryItemMapper {

    @Mapping(target = "inventory", source = "inventoryId")
    InventoryItem toEntity(RegisterInventoryItemUseCase.RegisterInventoryItemCommand command);

    InventoryItemDto toOutputModel(InventoryItem inventoryItem);
}
