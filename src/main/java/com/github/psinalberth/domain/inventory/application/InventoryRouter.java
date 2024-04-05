package com.github.psinalberth.domain.inventory.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InventoryRouter {

    @Bean
    RouterFunction<ServerResponse> inventoryRoutes(final InventoryHandler handler) {
        return route()
                .path("/v1/inventories", builder -> builder
                        .POST("", handler::createInventory)
                        .GET("/{code}", handler::fetchInventory)
                )
                .build();
    }
}
