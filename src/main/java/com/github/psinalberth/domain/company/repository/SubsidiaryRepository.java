package com.github.psinalberth.domain.company.repository;

import com.github.psinalberth.domain.company.model.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Long> {

    Optional<Subsidiary> findByName(String name);
}
