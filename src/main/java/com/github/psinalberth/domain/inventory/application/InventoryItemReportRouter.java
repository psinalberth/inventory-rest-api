package com.github.psinalberth.domain.inventory.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InventoryItemReportRouter {

    @Bean
    RouterFunction<ServerResponse> reportRoutes(final InventoryItemReportHandler handler) {
        return route()
                .path("/v1/reports/inventory/{code}/items", builder -> builder
                        .GET("", handler::doExport)
                )
                .build();
    }
}
