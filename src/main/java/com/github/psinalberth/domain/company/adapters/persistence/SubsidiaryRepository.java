package com.github.psinalberth.domain.company.adapters.persistence;

import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;
import com.github.psinalberth.domain.company.application.port.outgoing.LoadSubsidiaryPort;
import com.github.psinalberth.domain.company.application.port.outgoing.SaveSubsidiaryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Long>, LoadSubsidiaryPort, SaveSubsidiaryPort {

    Optional<Subsidiary> findByName(String name);
}
