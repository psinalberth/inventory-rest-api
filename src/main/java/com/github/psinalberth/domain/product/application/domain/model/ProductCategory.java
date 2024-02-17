package com.github.psinalberth.domain.product.application.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @OneToMany
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
