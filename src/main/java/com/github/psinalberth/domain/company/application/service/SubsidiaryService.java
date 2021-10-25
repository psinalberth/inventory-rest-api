package com.github.psinalberth.domain.company.application.service;

import com.github.psinalberth.domain.company.application.port.outgoing.LoadSubsidiaryPort;
import com.github.psinalberth.domain.company.application.port.outgoing.SaveSubsidiaryPort;
import com.github.psinalberth.domain.shared.domain.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SubsidiaryService {

    private final LoadSubsidiaryPort loadSubsidiaryPort;
    private final SaveSubsidiaryPort saveSubsidiaryPort;
}
