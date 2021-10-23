package com.github.psinalberth.domain.inventory.service;

import com.github.psinalberth.domain.inventory.exception.BatchTypeNotFoundException;
import com.github.psinalberth.domain.inventory.mapper.InventoryItemMapper;
import com.github.psinalberth.domain.inventory.model.BatchType;
import com.github.psinalberth.domain.inventory.model.InventoryItem;
import com.github.psinalberth.domain.inventory.dto.output.InventoryItemDto;
import com.github.psinalberth.domain.inventory.repository.BatchTypeRepository;
import com.github.psinalberth.domain.inventory.repository.InventoryItemRepository;
import com.github.psinalberth.domain.inventory.usecase.QueryInventoryItemsUseCase;
import com.github.psinalberth.domain.inventory.usecase.RegisterInventoryItemUseCase;
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