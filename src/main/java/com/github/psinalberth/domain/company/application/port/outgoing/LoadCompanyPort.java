package com.github.psinalberth.domain.company.application.port.outgoing;

import com.github.psinalberth.domain.company.application.domain.model.Company;

import java.util.Optional;

public interface LoadCompanyPort {

    Optional<Company> findByName(String name);
}
