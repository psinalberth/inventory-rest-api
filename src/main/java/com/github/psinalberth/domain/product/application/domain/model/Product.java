package com.github.psinalberth.domain.product.application.domain.model;

import com.github.psinalberth.domain.shared.domain.model.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity {

    private String productId;
    private SortedSet<Barcode> barcodes;
    private Set<ProductCategory> categories;
    private Set<ProductGroup> groups;

    public boolean addBarcode(Barcode barcode) {
        if (this.barcodes == null)
            this.barcodes = new TreeSet<>(Comparator.comparing(Barcode::getBarCode));
        return this.barcodes.add(barcode);
    }

    public boolean removeBarcode(Barcode barcode) {
        if (this.barcodes == null)
            return true;
        return this.barcodes.remove(barcode);
    }

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