package com.github.psinalberth.domain.product.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProductCategory implements IProductCategory {

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
