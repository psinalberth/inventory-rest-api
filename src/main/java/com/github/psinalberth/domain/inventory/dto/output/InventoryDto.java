package com.github.psinalberth.domain.inventory.dto.output;

import com.github.psinalberth.domain.company.dto.output.CompanyDto;
import com.github.psinalberth.domain.company.dto.output.SubsidiaryDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class InventoryDto {

    @Schema(hidden = true)
    private Long inventoryId;
    private String accessCode;
    private String title;
    private CompanyDto company;
    private SubsidiaryDto subsidiary;
    private List<BatchTypeDto> batchTypes;
    private LocalDateTime createdAt;
}