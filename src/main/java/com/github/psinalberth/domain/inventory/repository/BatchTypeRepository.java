package com.github.psinalberth.domain.inventory.repository;

import com.github.psinalberth.domain.inventory.model.BatchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchTypeRepository extends JpaRepository<BatchType, Long> {

    Optional<BatchType> findByName(String name);
}
