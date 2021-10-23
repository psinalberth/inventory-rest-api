package com.github.psinalberth.domain.inventory.application.service;

import com.github.psinalberth.domain.inventory.application.domain.exception.BatchTypeNotFoundException;
import com.github.psinalberth.domain.inventory.adapters.mapper.InventoryItemMapper;
import com.github.psinalberth.domain.inventory.application.domain.model.BatchType;
import com.github.psinalberth.domain.inventory.application.domain.model.InventoryItem;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import com.github.psinalberth.domain.inventory.adapters.persistence.BatchTypeRepository;
import com.github.psinalberth.domain.inventory.adapters.persistence.InventoryItemRepository;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryItemsUseCase;
import com.github.psinalberth.domain.inventory.application.port.incoming.RegisterInventoryItemUseCase;
import com.github.psinalberth.domain.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class InventoryItemService implements RegisterInventoryItemUseCase, QueryInventoryItemsUseCase {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper inventoryItemMapper;
    private final BatchTypeRepository batchTypeRepository;
    private final InventoryQueries inventoryQueries;

    @Override
    public InventoryItemDto register(RegisterInventoryItemCommand command) {

        InventoryItem inventoryItem = inventoryItemMapper.toEntity(command);
        BatchType batchType = batchTypeRepository.findById(command.getBatchTypeId())
                .orElseThrow(() -> new BatchTypeNotFoundException(command.getBatchTypeId()));

        inventoryItem.setBatchType(batchType);

        return inventoryItemMapper.toOutputModel(inventoryItemRepository.save(inventoryItem));
    }

    @Override
    public List<InventoryItemDto> query(QueryInventoryItemsCommand command) {
        return inventoryQueries.queryItems(command);
    }
}