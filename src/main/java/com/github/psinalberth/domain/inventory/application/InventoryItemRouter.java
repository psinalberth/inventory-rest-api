package com.github.psinalberth.domain.inventory.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class InventoryItemRouter {

    @Bean
    RouterFunction<ServerResponse> inventoryItemRoutes(final InventoryItemHandler handler) {
        return route()
                .path("/v1/inventories/{code}/items", builder -> builder
                        .POST("", handler::registerItem)
                        .GET("", handler::listItems)
                )
                .build();
    }
}
