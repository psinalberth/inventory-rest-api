package com.github.psinalberth.domain.company.application.domain.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubsidiaryDto {

    @Schema(example = "12")
    private Long subsidiaryId;

    @Schema(example = "North Sub.")
    private String name;
}
