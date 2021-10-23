package com.github.psinalberth.domain.inventory.mapper;

import com.github.psinalberth.domain.company.model.Subsidiary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SubsidiaryMapper {

    @Mapping(target = "name", source = "name")
    Subsidiary toEntity(String name);
}
