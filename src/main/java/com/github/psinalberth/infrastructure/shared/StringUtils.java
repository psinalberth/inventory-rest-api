package com.github.psinalberth.infrastructure.shared;

import com.github.psinalberth.domain.shared.provider.GenerateRandomStringPort;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

@Component
public class StringUtils implements GenerateRandomStringPort {

    @Override
    public String generateRandom(int length) {
        return RandomString.make(length);
    }
}
