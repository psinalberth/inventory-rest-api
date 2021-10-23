package com.github.psinalberth.domain.company.application.port.outgoing;

import com.github.psinalberth.domain.company.application.domain.model.Company;

public interface SaveCompanyPort {

    Company save(Company company);
}
