package com.github.psinalberth.domain.inventory.adapters.mapper;

import com.github.psinalberth.domain.inventory.application.domain.model.BatchType;
import com.github.psinalberth.domain.inventory.application.domain.dto.BatchTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BatchTypeMapper {

    @Mapping(target = "name", source = "batchType")
    BatchType toEntity(String batchType);

    BatchType toEntity(Long batchTypeId);

    BatchTypeDto toOutputModel(BatchType batchType);
}
