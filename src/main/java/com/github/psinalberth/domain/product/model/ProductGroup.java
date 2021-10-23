package com.github.psinalberth.domain.product.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductGroup {

    private String groupId;
    private String name;
    private Set<ProductGroup> subGroups;

    public boolean hasSubGroups() {
        if (this.subGroups == null)
            return false;
        return !this.subGroups.isEmpty();
    }
}