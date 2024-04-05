package com.github.psinalberth.api.providers.apache;

import com.github.psinalberth.domain.shared.port.outgoing.RandomStringProvider;
import jakarta.inject.Named;
import org.apache.commons.lang3.RandomStringUtils;

@Named
public class CustomRandomStringGenerator implements RandomStringProvider {
    @Override
    public String generate(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }
}
