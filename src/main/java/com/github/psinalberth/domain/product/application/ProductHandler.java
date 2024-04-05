package com.github.psinalberth.domain.product.application;

import com.github.psinalberth.api.providers.reactor.ServerResponseExtensions;
import com.github.psinalberth.domain.inventory.core.model.Inventory;
import com.github.psinalberth.domain.inventory.core.model.InventoryGroup;
import com.github.psinalberth.domain.inventory.core.model.command.FetchInventoryCommand;
import com.github.psinalberth.domain.inventory.core.model.command.SaveManyProductCommand;
import com.github.psinalberth.domain.inventory.core.ports.incoming.FetchInventory;
import com.github.psinalberth.domain.inventory.core.ports.incoming.UpdateInventory;
import com.github.psinalberth.domain.product.core.model.Product;
import com.github.psinalberth.domain.product.core.model.command.FetchProductCommand;
import com.github.psinalberth.domain.product.core.ports.incoming.FetchProduct;
import com.github.psinalberth.domain.product.core.ports.incoming.SaveProducts;
import com.github.psinalberth.domain.shared.model.Result;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

import static com.github.psinalberth.api.providers.reactor.ServerResponseExtensions.handleOkResponse;

@Named
@RequiredArgsConstructor
public class ProductHandler {

    private final SaveProducts saveProducts;
    private final FetchProduct fetchProduct;
    private final FetchInventory fetchInventory;
    private final UpdateInventory updateInventory;

    private static final String INVENTORY_ID = "code";

    public Mono<ServerResponse> saveMany(final ServerRequest request) {
        var inventoryId = request.pathVariable(INVENTORY_ID);
        return request.body(BodyExtractors.toMultipartData())
                .map(filePart -> filePart.getFirst("file"))
                .map(Part::content)
                .flatMap(DataBufferUtils::join)
                .map(DataBuffer::asInputStream)
                .map(inputStream -> new SaveManyProductCommand(inventoryId, inputStream))
                .flatMap(command -> Mono.fromFuture(() -> saveProducts.saveMany(command)))
                .zipWith(Mono.fromFuture(fetchInventory.fetch(new FetchInventoryCommand(inventoryId))))
                .flatMap(this::updateInventory)
                .flatMap(e -> ServerResponse.ok().build());
    }

    private Mono<Result<Inventory>> updateInventory(final Tuple2<Result<List<Product>>, Result<Inventory>> tuple) {
        var groups = mapGroups(tuple.getT1().getValue());
        var inventory = tuple.getT2().getValue().withGroups(groups);

        return Mono.fromFuture(() -> updateInventory.update(inventory));
    }

    private List<InventoryGroup> mapGroups(final List<Product> products) {
        return products.stream()
                .map(Product::group)
                .map(group -> new InventoryGroup(group.code()))
                .distinct()
                .toList();
    }

    public Mono<ServerResponse> fetch(final ServerRequest request) {
        return Mono.just(new FetchProductCommand(request.pathVariable(INVENTORY_ID), request.pathVariable("productId")))
                .flatMap(command -> Mono.fromFuture(() -> fetchProduct.fetch(command)))
                .flatMap(result -> handleOkResponse(request, result));
    }
}
