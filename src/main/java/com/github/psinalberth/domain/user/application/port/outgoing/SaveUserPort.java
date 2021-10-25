package com.github.psinalberth.domain.user.application.port.outgoing;

import com.github.psinalberth.domain.user.application.domain.model.ApplicationUser;

public interface SaveUserPort {

    ApplicationUser save(ApplicationUser user);
}
