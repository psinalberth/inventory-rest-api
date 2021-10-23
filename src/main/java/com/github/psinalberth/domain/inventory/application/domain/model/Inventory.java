package com.github.psinalberth.domain.inventory.application.domain.model;

import com.github.psinalberth.domain.company.application.domain.model.Company;
import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;
import com.github.psinalberth.domain.shared.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Inventory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private String code;

    private String title;

    @JoinColumn(name = "company_id")
    @ManyToOne
    private Company company;

    @JoinColumn(name = "subsidiary_id")
    @ManyToOne
    private Subsidiary subsidiary;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL)
    private List<BatchType> batchTypes = new ArrayList<>();
}