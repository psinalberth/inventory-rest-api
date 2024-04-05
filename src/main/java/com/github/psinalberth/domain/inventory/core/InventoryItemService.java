package com.github.psinalberth.domain.inventory.core;

import com.github.psinalberth.domain.inventory.core.model.Inventory;
import com.github.psinalberth.domain.inventory.core.model.InventoryGroup;
import com.github.psinalberth.domain.inventory.core.model.InventoryItem;
import com.github.psinalberth.domain.inventory.core.model.command.ListInventoryItemCommand;
import com.github.psinalberth.domain.inventory.core.model.command.RegisterInventoryItemCommand;
import com.github.psinalberth.domain.inventory.core.ports.incoming.InventoryItemMapper;
import com.github.psinalberth.domain.inventory.core.ports.incoming.ListItems;
import com.github.psinalberth.domain.inventory.core.ports.incoming.RegisterInventoryItem;
import com.github.psinalberth.domain.inventory.core.ports.outgoing.InventoryItemRepository;
import com.github.psinalberth.domain.inventory.core.ports.outgoing.InventoryRepository;
import com.github.psinalberth.domain.product.core.exception.ProductNotFoundException;
import com.github.psinalberth.domain.product.core.ports.outgoing.BatchTypeRepository;
import com.github.psinalberth.domain.product.core.ports.outgoing.ProductRepository;
import com.github.psinalberth.domain.shared.exception.BusinessException;
import com.github.psinalberth.domain.shared.exception.ResourceNotFoundException;
import com.github.psinalberth.domain.shared.model.Result;
import com.github.psinalberth.domain.shared.port.outgoing.ConfigProvider;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Named
@RequiredArgsConstructor
public class InventoryItemService implements RegisterInventoryItem, ListItems {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryItemRepository itemRepository;
    private final BatchTypeRepository batchTypeRepository;
    private final InventoryItemMapper itemMapper;
    private final ConfigProvider configProvider;

    @Override
    public CompletableFuture<Result<InventoryItem>> register(final RegisterInventoryItemCommand command) {

        var inventory = Result.fromFuture(() -> inventoryRepository.fetch(command.inventoryId()));

        if (inventory.isEmpty()) {
            return Result.of(InventoryItem.class)
                    .fail(() -> new ResourceNotFoundException("Inventory not found with id " + command.inventoryId()))
                    .toFuture();
        }

        return Result.fromFuture(() -> itemRepository.findById(
                        command.inventoryId(),
                        command.productId(),
                        command.batchTypeId())
                )
                .map(item -> updateItem(command, item))
                .onEmptySwitchTo(() -> createItem(command, inventory))
                .flatMap(item -> Result.fromFuture(() -> itemRepository.save(item)))
                .toFuture();
    }

    private Result<InventoryItem> createItem(
            final RegisterInventoryItemCommand command,
            final Result<Inventory> inventory
    ) {
        return Result.fromSupplier(() -> itemMapper.toInventoryItem(command))
                .flatMap(item -> mapProduct(inventory.getValue(), command.productId(), item))
                .flatMap(item -> validateItemGroup(inventory.getValue(), item))
                .flatMap(item -> mapBatchType(command.batchTypeId(), item));
    }

    private static InventoryItem updateItem(
            final RegisterInventoryItemCommand command,
            final InventoryItem item
    ) {
        var newQuantity = item.quantity().add(command.quantity());
        return item.withQuantity(newQuantity);
    }

    private Result<InventoryItem> mapBatchType(final String batchTypeId, final InventoryItem item) {
        return Result.fromFuture(() -> batchTypeRepository.findById(item.inventoryId(), batchTypeId))
                .map(p -> item.withBatchType(p.id()))
                .onEmptyFailWith(() -> new ResourceNotFoundException("Batch type not found with id " + batchTypeId));
    }

    private Result<InventoryItem> validateItemGroup(final Inventory inventory, final InventoryItem item) {
        return Result.success(inventory)
                .map(Inventory::groups)
                .map(groups -> groups.stream().map(InventoryGroup::code))
                .map(groups -> groups.anyMatch(group -> group.equals(item.groupId())))
                .flatMap(result -> result ? Result.success(item) : Result.failure(new BusinessException("Group does not belong to inventory " + inventory.id())));
    }

    private Result<InventoryItem> mapProduct(
            final Inventory inventory,
            final String productId,
            final InventoryItem item
    ) {
        return Result.fromFuture(() -> productRepository.findById(inventory.code(), productId))
                .onEmptyTransform(() -> productRepository.findById(configProvider.getDefaultInventoryId(), productId))
                .map(item::withProduct)
                .onEmptyFailWith(() -> new ProductNotFoundException("Product not found with id " + productId));
    }

    @Override
    public CompletableFuture<Result<List<InventoryItem>>> findAll(final ListInventoryItemCommand command) {
        return Result.fromFuture(() -> itemRepository.findAll(command.inventoryId()))
                .toFuture();
    }
}
