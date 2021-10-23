package com.github.psinalberth.domain.company.application.service;

import com.github.psinalberth.domain.company.application.domain.model.Company;
import com.github.psinalberth.domain.company.application.port.outgoing.LoadCompanyPort;
import com.github.psinalberth.domain.company.application.port.outgoing.SaveCompanyPort;
import com.github.psinalberth.domain.shared.annotation.UseCase;
import com.github.psinalberth.domain.shared.application.port.RetrieveCompanyPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CompanyService implements RetrieveCompanyPort {

    private final LoadCompanyPort loadCompanyPort;
    private final SaveCompanyPort saveCompanyPort;

    @Override
    public Company retrieve(String name) {
        return loadCompanyPort.findByName(name)
            .orElseGet(() -> {
                Company company = new Company();
                company.setName(name);
                return saveCompanyPort.save(company);
            });
    }
}
