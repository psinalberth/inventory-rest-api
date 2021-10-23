package com.github.psinalberth.domain.shared.application.port;

import com.github.psinalberth.domain.company.application.domain.model.Company;

public interface RetrieveCompanyPort {

    Company retrieve(String name);
}
