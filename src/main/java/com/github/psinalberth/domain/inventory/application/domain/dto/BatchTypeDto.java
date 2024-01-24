package com.github.psinalberth.domain.inventory.application.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchTypeDto {

    @Schema(example = "3")
    private Long batchTypeId;

    @Schema(example = "Room 147")
    private String name;
}
