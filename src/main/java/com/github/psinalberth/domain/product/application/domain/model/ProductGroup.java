package com.github.psinalberth.domain.product.application.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String name;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @OneToMany
    private Set<ProductGroup> subGroups;

    public boolean hasSubGroups() {
        if (this.subGroups == null)
            return false;
        return !this.subGroups.isEmpty();
    }
}