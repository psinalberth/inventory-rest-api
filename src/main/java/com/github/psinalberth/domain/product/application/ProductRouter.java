package com.github.psinalberth.domain.product.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    RouterFunction<ServerResponse> productRoutes(final ProductHandler handler) {
        return route()
                .path("/v1/inventories/{code}/products", builder -> builder
                        .POST("", handler::saveMany)
                        .GET("/{productId}", handler::fetch)
                )
                .build();
    }
}
