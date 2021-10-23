package com.github.psinalberth.infrastructure.shared;

import com.github.psinalberth.domain.shared.provider.StringProvider;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

@Component
public class CustomStringProvider implements StringProvider {

    @Override
    public String generateRandom(int length) {
        return RandomString.make(length);
    }
}
