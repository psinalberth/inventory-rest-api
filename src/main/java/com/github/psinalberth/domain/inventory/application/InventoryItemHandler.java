package com.github.psinalberth.domain.inventory.application;

import com.github.psinalberth.domain.inventory.core.model.command.ListInventoryItemCommand;
import com.github.psinalberth.domain.inventory.core.model.command.RegisterInventoryItemCommand;
import com.github.psinalberth.domain.inventory.core.ports.incoming.ListItems;
import com.github.psinalberth.domain.inventory.core.ports.incoming.RegisterInventoryItem;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.github.psinalberth.api.providers.reactor.ServerResponseExtensions.handleCreatedResponse;
import static com.github.psinalberth.api.providers.reactor.ServerResponseExtensions.handleOkResponse;

@Named
@RequiredArgsConstructor
public class InventoryItemHandler {

    private final RegisterInventoryItem registerInventoryItem;
    private final ListItems listItems;

    public Mono<ServerResponse> registerItem(final ServerRequest request) {
        return request
                .bodyToMono(RegisterInventoryItemCommand.class)
                .map(command -> command.withInventoryId(request.pathVariable("code")))
                .flatMap(command -> Mono.fromFuture(() -> registerInventoryItem.register(command)))
                .flatMap(result -> handleCreatedResponse(request, result));
    }

    public Mono<ServerResponse> listItems(final ServerRequest request) {
        var id = request.pathVariable("code");
        return Mono.fromFuture(() -> listItems.findAll(new ListInventoryItemCommand(id)))
                .flatMap(result -> handleOkResponse(request, result));
    }
}
