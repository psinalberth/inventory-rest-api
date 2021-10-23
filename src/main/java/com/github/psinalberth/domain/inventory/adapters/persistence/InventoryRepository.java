package com.github.psinalberth.domain.inventory.adapters.persistence;

import com.github.psinalberth.domain.inventory.application.domain.model.Inventory;
import com.github.psinalberth.domain.inventory.application.port.outgoing.LoadInventoryPort;
import com.github.psinalberth.domain.inventory.application.port.outgoing.SaveInventoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>, LoadInventoryPort, SaveInventoryPort {

    @Query("select i from Inventory i " +
            "join fetch i.company " +
            "join fetch i.subsidiary " +
            "join fetch i.batchTypes " +
            "where i.code = :code")
    Optional<Inventory> findByCode(String code);
}
