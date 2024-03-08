package com.github.psinalberth.domain.product.application.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    @Schema(example = "78912345678901")
    private String productId;

    @Schema(example = "Rice")
    private String name;

    @Schema(example = "Rice")
    private String category;

    @Schema(example = "Rice")
    private String group;

    @Schema(example = "Rice")
    private String department;

    @Schema(example = "8.99")
    private BigDecimal price;
}
