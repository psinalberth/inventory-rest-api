package com.github.psinalberth.domain.inventory.application;

import com.github.psinalberth.domain.inventory.core.model.command.CreateInventoryCommand;
import com.github.psinalberth.domain.inventory.core.model.command.FetchInventoryCommand;
import com.github.psinalberth.domain.inventory.core.ports.incoming.CreateInventory;
import com.github.psinalberth.domain.inventory.core.ports.incoming.FetchInventory;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.github.psinalberth.api.providers.reactor.ServerResponseExtensions.handleCreatedResponse;
import static com.github.psinalberth.api.providers.reactor.ServerResponseExtensions.handleOkResponse;

@Named
@RequiredArgsConstructor
public class InventoryHandler {

    private final CreateInventory createInventory;
    private final FetchInventory fetchInventory;

    private static final String INVENTORY_ID = "code";

    public Mono<ServerResponse> createInventory(final ServerRequest request) {
        return request.bodyToMono(CreateInventoryCommand.class)
                .flatMap(command -> Mono.fromFuture(() -> createInventory.create(command)))
                .flatMap(result -> handleCreatedResponse(request, result));
    }

    public Mono<ServerResponse> fetchInventory(final ServerRequest request) {
        return Mono.just(request.pathVariable(INVENTORY_ID))
                .map(FetchInventoryCommand::new)
                .flatMap(command -> Mono.fromFuture(() -> fetchInventory.fetch(command)))
                .flatMap(result -> handleOkResponse(request, result));
    }
}
