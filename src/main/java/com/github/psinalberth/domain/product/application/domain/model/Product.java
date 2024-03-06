package com.github.psinalberth.domain.product.application.domain.model;

import com.github.psinalberth.domain.shared.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Product extends BaseEntity implements IProduct {

    @Id
    private String productId;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductCategory category;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductGroup group;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductDepartment department;
}