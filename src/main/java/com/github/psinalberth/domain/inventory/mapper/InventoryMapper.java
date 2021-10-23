package com.github.psinalberth.domain.inventory.mapper;

import com.github.psinalberth.domain.inventory.model.Inventory;
import com.github.psinalberth.domain.inventory.dto.output.InventoryDto;
import com.github.psinalberth.domain.inventory.usecase.CreateInventoryUseCase;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {
        BatchTypeMapper.class,
        CompanyMapper.class,
        SubsidiaryMapper.class
})
public interface InventoryMapper {

    Inventory toEntity(CreateInventoryUseCase.CreateInventoryCommand command);

    @Mapping(target = "inventoryId", source = "inventoryId")
    Inventory toEntity(Long inventoryId);

    @Mapping(target = "accessCode", source = "code")
    InventoryDto toOutputModel(Inventory inventory);
}
