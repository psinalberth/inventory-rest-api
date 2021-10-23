package com.github.psinalberth.domain.company.application.port.outgoing;

import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;

import java.util.Optional;

public interface LoadSubsidiaryPort {

    Optional<Subsidiary> findByName(String name);
}
