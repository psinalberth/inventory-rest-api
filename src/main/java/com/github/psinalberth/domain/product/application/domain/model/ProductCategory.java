package com.github.psinalberth.domain.product.application.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ProductCategory {

    private String categoryId;
    private String name;
    private Set<ProductCategory> subCategories;

    public boolean hasSubCategories() {
        if (this.subCategories == null)
            return false;
        return !this.subCategories.isEmpty();
    }

    public boolean addCategory(ProductCategory category) {
        if (this.subCategories == null)
            this.subCategories = new HashSet<>();
        return this.subCategories.add(category);
    }

    public boolean removeCategory(ProductCategory category) {
        if (this.subCategories == null)
            return true;
        return this.subCategories.remove(category);
    }
}
