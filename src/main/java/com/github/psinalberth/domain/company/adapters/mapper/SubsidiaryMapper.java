package com.github.psinalberth.domain.company.adapters.mapper;

import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SubsidiaryMapper {

    @Mapping(target = "name", source = "name")
    Subsidiary toEntity(String name);
}
