package com.github.psinalberth.domain.company.application.port.outgoing;

import com.github.psinalberth.domain.company.application.domain.model.Subsidiary;

public interface SaveSubsidiaryPort {

    Subsidiary save(Subsidiary subsidiary);
}
