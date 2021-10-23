package com.github.psinalberth.domain.inventory.application.service;

import com.github.psinalberth.domain.inventory.application.domain.model.BatchType;
import com.github.psinalberth.domain.inventory.application.domain.model.InventoryItem;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import com.github.psinalberth.domain.inventory.application.domain.dto.BatchTypeDto;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryItemsUseCase;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryQueries {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<InventoryItemDto> queryItems(QueryInventoryItemsUseCase.QueryInventoryItemsCommand command) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple>  query = builder.createQuery(Tuple.class);
        Root<InventoryItem> root = query.from(InventoryItem.class);

        // Paths

        Path<Long> inventoryId = root.get("inventory").get("inventoryId");
        Path<String> productId = root.get("productId");
        Path<BatchType> batchType = root.get("batchType");
        Path<Long> batchTypeId = batchType.get("batchTypeId");
        Path<String> batchTypeName = batchType.get("name");

        // Query

        query.multiselect(productId, batchTypeId, batchTypeName, builder.sum(root.get("quantity")));
        query.where(builder.equal(root.get("inventory").get("code"), command.getCode().toUpperCase()));
        query.groupBy(inventoryId, productId, batchTypeId, batchTypeName);
        query.orderBy(new OrderImpl(productId), new OrderImpl(batchTypeId));

        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .map(tuple -> new InventoryItemDto(
                        String.valueOf(tuple.get(0)),
                        new BatchTypeDto(Long.parseLong(String.valueOf(tuple.get(1))), String.valueOf(tuple.get(2))),
                        new BigDecimal(String.valueOf(tuple.get(3))))).collect(Collectors.toList());
    }
}
