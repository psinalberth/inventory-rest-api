package com.github.psinalberth.domain.company.application.domain.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {

    @Schema(example = "1")
    private Long companyId;

    @Schema(example = "My Company Inc.")
    private String name;
}
