package com.github.psinalberth.domain.company.application.service;

import com.github.psinalberth.domain.company.application.port.outgoing.LoadCompanyPort;
import com.github.psinalberth.domain.company.application.port.outgoing.SaveCompanyPort;
import com.github.psinalberth.domain.shared.domain.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CompanyService {

    private final LoadCompanyPort loadCompanyPort;
    private final SaveCompanyPort saveCompanyPort;
}
