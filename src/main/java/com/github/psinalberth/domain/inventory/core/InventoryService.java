package com.github.psinalberth.domain.inventory.core;

import com.github.psinalberth.domain.inventory.core.model.Inventory;
import com.github.psinalberth.domain.inventory.core.model.command.CreateInventoryCommand;
import com.github.psinalberth.domain.inventory.core.model.command.FetchInventoryCommand;
import com.github.psinalberth.domain.inventory.core.ports.incoming.CreateInventory;
import com.github.psinalberth.domain.inventory.core.ports.incoming.FetchInventory;
import com.github.psinalberth.domain.inventory.core.ports.incoming.InventoryMapper;
import com.github.psinalberth.domain.inventory.core.ports.incoming.UpdateInventory;
import com.github.psinalberth.domain.inventory.core.ports.outgoing.InventoryRepository;
import com.github.psinalberth.domain.shared.exception.ResourceNotFoundException;
import com.github.psinalberth.domain.shared.model.Result;
import com.github.psinalberth.domain.shared.port.outgoing.RandomStringProvider;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Named
@RequiredArgsConstructor
public class InventoryService implements CreateInventory, FetchInventory, UpdateInventory {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper mapper;
    private final RandomStringProvider randomStringGenerator;

    @Override
    public CompletableFuture<Result<Inventory>> create(final CreateInventoryCommand command) {
        return Result.fromSupplier(() -> mapper.toInventory(command))
                .map(inventory -> inventory.withCode(randomStringGenerator.generate(6)))
                .flatMap(inventory -> Result.fromFuture(() -> inventoryRepository.save(inventory)))
                .toFuture();
    }

    @Override
    public CompletableFuture<Result<Inventory>> fetch(final FetchInventoryCommand command) {
        return Result.fromSupplier(() -> command)
                .flatMap(cmd -> Result.fromFuture(() -> inventoryRepository.fetch(cmd.code())))
                .onEmptyFailWith(() -> new ResourceNotFoundException("Inventory not found with code " + command.code()))
                .toFuture();
    }

    @Override
    public CompletableFuture<Result<Inventory>> update(final Inventory inventory) {
        return Result.fromFuture(() -> inventoryRepository.save(inventory))
                .toFuture();
    }
}
