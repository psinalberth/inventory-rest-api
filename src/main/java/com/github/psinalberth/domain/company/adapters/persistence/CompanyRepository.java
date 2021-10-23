package com.github.psinalberth.domain.company.adapters.persistence;

import com.github.psinalberth.domain.company.application.domain.model.Company;
import com.github.psinalberth.domain.company.application.port.outgoing.LoadCompanyPort;
import com.github.psinalberth.domain.company.application.port.outgoing.SaveCompanyPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, LoadCompanyPort, SaveCompanyPort {

    Optional<Company> findByName(String name);
}
