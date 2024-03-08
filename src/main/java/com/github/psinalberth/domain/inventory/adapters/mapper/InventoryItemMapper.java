package com.github.psinalberth.domain.inventory.adapters.mapper;

import com.github.psinalberth.domain.inventory.application.domain.model.InventoryItem;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import com.github.psinalberth.domain.inventory.application.port.incoming.RegisterInventoryItemUseCase;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        InventoryMapper.class
})
public interface InventoryItemMapper {

    @Mapping(target = "inventory", source = "inventoryId")
    InventoryItem toEntity(RegisterInventoryItemUseCase.RegisterInventoryItemCommand command);

    @Mapping(target = "productId", source = "product.productId")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "price", source = "product.price")
    InventoryItemDto toOutputModel(InventoryItem inventoryItem);
}
