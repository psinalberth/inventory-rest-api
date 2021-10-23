package com.github.psinalberth.domain.company.application.service;

import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;
import com.github.psinalberth.domain.company.application.port.outgoing.LoadSubsidiaryPort;
import com.github.psinalberth.domain.company.application.port.outgoing.SaveSubsidiaryPort;
import com.github.psinalberth.domain.shared.annotation.UseCase;
import com.github.psinalberth.domain.shared.application.port.RetrieveSubsidiaryPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SubsidiaryService implements RetrieveSubsidiaryPort {

    private final LoadSubsidiaryPort loadSubsidiaryPort;
    private final SaveSubsidiaryPort saveSubsidiaryPort;

    @Override
    public Subsidiary retrieve(String name) {
        return loadSubsidiaryPort.findByName(name)
            .orElseGet(() -> {
                Subsidiary subsidiary = new Subsidiary();
                subsidiary.setName(name);
                return saveSubsidiaryPort.save(subsidiary);
            });
    }
}
