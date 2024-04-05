package com.github.psinalberth.domain.inventory.application;

import com.github.psinalberth.api.providers.reactor.ServerResponseExtensions;
import com.github.psinalberth.domain.inventory.infrastructure.extractor.InventoryItemDataExporter;
import com.github.psinalberth.domain.product.infrastructure.database.InventoryItemQuery;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Named
@RequiredArgsConstructor
public class InventoryItemReportHandler {

    private final InventoryItemQuery inventoryItemQuery;
    private final InventoryItemDataExporter exporter;

    public Mono<ServerResponse> doExport(final ServerRequest request) {
        var id = request.pathVariable("code");
        return inventoryItemQuery.queryItems(id)
                .collectList()
                .map(exporter::doExport)
                .flatMap(ServerResponseExtensions::makeCsvSuccessResponse);
    }
}
