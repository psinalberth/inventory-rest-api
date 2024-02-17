package com.github.psinalberth.domain.product.application.domain.model;

import com.github.psinalberth.domain.shared.domain.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Product extends BaseEntity {

    @Id
    private String productId;

    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductCategory> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductGroup> groups;

    public boolean addCategory(ProductCategory category) {
        if (this.categories == null)
            this.categories = new HashSet<>();
        return this.categories.add(category);
    }

    public boolean removeCategory(ProductCategory category) {
        if (this.categories == null)
            return true;
        return this.categories.remove(category);
    }

    public boolean addGroup(ProductGroup group) {
        if (this.groups == null)
            this.groups = new HashSet<>();
        return this.groups.add(group);
    }

    public boolean removeGroup(ProductGroup group) {
        if (this.groups == null)
            return true;
        return this.groups.remove(group);
    }
}