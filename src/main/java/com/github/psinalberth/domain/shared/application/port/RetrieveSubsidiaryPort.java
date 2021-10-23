package com.github.psinalberth.domain.shared.application.port;

import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;

public interface RetrieveSubsidiaryPort {

    Subsidiary retrieve(String name);
}
