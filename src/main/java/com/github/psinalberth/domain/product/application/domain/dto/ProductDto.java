package com.github.psinalberth.domain.product.application.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {

    @Schema(example = "78912345678901")
    private String productId;

    @Schema(example = "Rice")
    private String name;

    @Schema(example = "[ \"Rice\" ]")
    private List<String> categories;

    @Schema(example = "[ \"Rice\" ]")
    private List<String> groups;
}
