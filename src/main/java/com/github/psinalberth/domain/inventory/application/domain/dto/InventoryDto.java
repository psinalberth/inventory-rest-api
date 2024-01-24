package com.github.psinalberth.domain.inventory.application.domain.dto;

import com.github.psinalberth.domain.company.application.domain.output.CompanyDto;
import com.github.psinalberth.domain.company.application.domain.output.SubsidiaryDto;
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

    @Schema(example = "X78ZAQ")
    private String accessCode;

    @Schema(example = "My awesome inventory")
    private String title;
    private CompanyDto company;
    private SubsidiaryDto subsidiary;
    private List<BatchTypeDto> batchTypes;

    @Schema(example = "2021-11-02 09:47:10")
    private LocalDateTime createdAt;
}