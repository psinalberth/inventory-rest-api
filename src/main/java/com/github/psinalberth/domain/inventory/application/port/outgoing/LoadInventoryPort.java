package com.github.psinalberth.domain.inventory.application.port.outgoing;

import com.github.psinalberth.domain.inventory.application.domain.model.Inventory;

import java.util.Optional;

public interface LoadInventoryPort {

    Optional<Inventory> findByCode(String code);
}
