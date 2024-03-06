package com.github.psinalberth.domain.inventory.application.service;

import com.github.psinalberth.domain.inventory.application.domain.dto.BatchTypeDto;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import com.github.psinalberth.domain.inventory.application.domain.model.BatchType;
import com.github.psinalberth.domain.inventory.application.domain.model.InventoryItem;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryItemsUseCase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryQueries {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<InventoryItemDto> queryItems(QueryInventoryItemsUseCase.QueryInventoryItemsCommand command) {

        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(Tuple.class);
        var root = query.from(InventoryItem.class);

        // Paths

        Path<Long> inventoryId = root.get("inventory").get("inventoryId");
        Path<String> productId = root.get("productId");
        Path<BatchType> batchType = root.get("batchType");
        Path<Long> batchTypeId = batchType.get("batchTypeId");
        Path<String> batchTypeName = batchType.get("name");

        // Query

        query.multiselect(productId, batchTypeId, batchTypeName, builder.sum(root.get("quantity")));
        query.where(builder.equal(root.get("inventory").get("code"), command.code().toUpperCase()));
        query.groupBy(inventoryId, productId, batchTypeId, batchTypeName);
        query.orderBy(builder.asc(productId), builder.asc(batchTypeId));

        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .map(tuple -> new InventoryItemDto(
                        String.valueOf(tuple.get(0)),
                        new BatchTypeDto(Long.parseLong(String.valueOf(tuple.get(1))), String.valueOf(tuple.get(2))),
                        new BigDecimal(String.valueOf(tuple.get(3))))).collect(Collectors.toList());
    }
}
