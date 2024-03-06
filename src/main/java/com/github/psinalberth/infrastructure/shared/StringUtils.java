package com.github.psinalberth.infrastructure.shared;

import com.github.psinalberth.domain.shared.provider.GenerateRandomStringPort;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class StringUtils implements GenerateRandomStringPort {

    @Override
    public String generateRandom(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
