package com.github.psinalberth.domain.inventory.adapters.mapper;

import com.github.psinalberth.domain.company.adapters.mapper.CompanyMapper;
import com.github.psinalberth.domain.company.adapters.mapper.SubsidiaryMapper;
import com.github.psinalberth.domain.inventory.application.domain.model.Inventory;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryDto;
import com.github.psinalberth.domain.inventory.application.port.incoming.CreateInventoryUseCase;
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
