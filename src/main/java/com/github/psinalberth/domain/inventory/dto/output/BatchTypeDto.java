package com.github.psinalberth.domain.inventory.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchTypeDto {

    private Long batchTypeId;
    private String name;
}
