package com.github.psinalberth.domain.inventory.repository;

import com.github.psinalberth.domain.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>, JpaSpecificationExecutor<Inventory> {

    @Query("select i from Inventory i " +
            "join fetch i.company " +
            "join fetch i.subsidiary " +
            "join fetch i.batchTypes " +
            "where i.code = :code")
    Optional<Inventory> findByCode(String code);
}
