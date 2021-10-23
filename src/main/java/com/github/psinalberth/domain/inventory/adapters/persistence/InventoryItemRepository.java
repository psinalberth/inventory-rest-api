package com.github.psinalberth.domain.inventory.adapters.persistence;

import com.github.psinalberth.domain.inventory.application.domain.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
