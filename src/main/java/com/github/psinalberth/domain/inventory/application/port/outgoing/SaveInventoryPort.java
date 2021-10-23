package com.github.psinalberth.domain.inventory.application.port.outgoing;

import com.github.psinalberth.domain.inventory.application.domain.model.Inventory;

public interface SaveInventoryPort {

    Inventory save(Inventory inventory);
}
