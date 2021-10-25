package com.github.psinalberth.domain.user.application.port.outgoing;

import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;

public interface LoadUserPort {

    ApplicationUser load(String userId);
}
