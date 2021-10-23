package com.github.psinalberth.domain.inventory.mapper;

import com.github.psinalberth.domain.company.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CompanyMapper {

    @Mapping(target = "name", source = "company")
    Company toEntity(String company);
}
