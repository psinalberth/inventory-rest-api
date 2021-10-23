package com.github.psinalberth.domain.inventory.application.domain.model;

import com.github.psinalberth.domain.shared.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class InventoryItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryItemId;
    private String productId;
    private BigDecimal quantity;

    @JoinColumn(name = "batch_type_id")
    @ManyToOne
    private BatchType batchType;

    @JoinColumn(name = "inventory_id")
    @ManyToOne
    private Inventory inventory;
}
