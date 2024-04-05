package com.github.psinalberth.api.providers.config;

import com.github.psinalberth.domain.shared.port.outgoing.ConfigProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
class DefaultConfigProvider implements ConfigProvider {

    private final String defaultInventoryId;

    public DefaultConfigProvider(
            @Value("${app.config.inventory.default-id}")
            final String defaultInventoryId
    ) {
        this.defaultInventoryId = defaultInventoryId;
    }

    @Override
    public String getDefaultInventoryId() {
        return defaultInventoryId;
    }
}
