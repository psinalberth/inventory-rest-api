package com.github.psinalberth.domain.inventory.application.service;

import com.github.psinalberth.domain.inventory.application.domain.dto.BatchTypeDto;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryItemDto;
import com.github.psinalberth.domain.inventory.application.domain.dto.InventoryReportItemDto;
import com.github.psinalberth.domain.inventory.application.domain.model.BatchType;
import com.github.psinalberth.domain.inventory.application.domain.model.InventoryItem;
import com.github.psinalberth.domain.inventory.application.port.incoming.QueryInventoryItemsUseCase;
import com.github.psinalberth.domain.product.application.domain.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
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
        Path<Product> product = root.get("product");
        Path<String> productId = product.get("productId");
        Path<String> productName = product.get("name");
        Path<BatchType> batchType = root.get("batchType");
        Path<Long> batchTypeId = batchType.get("batchTypeId");
        Path<String> batchTypeName = batchType.get("name");

        // Query

        query.multiselect(
                productId,
                productName,
                batchTypeId,
                batchTypeName,
                builder.sum(root.get("quantity")),
                product.get("price")
        );

        query.where(builder.equal(root.get("inventory").get("code"), command.code().toUpperCase()));
        query.groupBy(inventoryId, productId, productName, batchTypeId, batchTypeName, product.get("price"));
        query.orderBy(builder.asc(productId), builder.asc(batchTypeId));

        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .map(InventoryQueries::makeItem)
                .collect(Collectors.toList());
    }

    public List<InventoryReportItemDto> queryReportItems(QueryInventoryItemsUseCase.QueryInventoryItemsCommand command) {

        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(Tuple.class);
        var root = query.from(InventoryItem.class);

        // Paths

        Path<Long> inventoryId = root.get("inventory").get("inventoryId");
        Path<Product> product = root.get("product");
        Path<String> productId = product.get("productId");
        Path<String> productName = product.get("name");
        Path<BatchType> batchType = root.get("batchType");
        Path<Long> batchTypeId = batchType.get("batchTypeId");
        Path<String> batchTypeName = batchType.get("name");

        // Query

        query.multiselect(
                productId,
                productName,
                product.get("price"),
                product.get("quantity"),
                builder.sum(root.get("quantity"))
        );

        query.where(builder.equal(root.get("inventory").get("code"), command.code().toUpperCase()));
        query.groupBy(inventoryId, productId, productName, product.get("price"), product.get("quantity"));
        query.orderBy(builder.asc(productId));

        return entityManager.createQuery(query)
                .getResultList()
                .stream()
                .map(InventoryQueries::makeReportItem)
                .collect(Collectors.toList());
    }

    private static InventoryItemDto makeItem(Tuple tuple) {
        return new InventoryItemDto(
                String.valueOf(tuple.get(0)),
                String.valueOf(tuple.get(1)),
                new BatchTypeDto(Long.parseLong(String.valueOf(tuple.get(2))), String.valueOf(tuple.get(3))),
                new BigDecimal(String.valueOf(tuple.get(4))),
                Optional.ofNullable(tuple.get(5)).map(value -> new BigDecimal(String.valueOf(value))).orElse(null)
        );
    }

    private static InventoryReportItemDto makeReportItem(Tuple tuple) {

        var price = Optional.ofNullable(tuple.get(2))
                .map(value -> new BigDecimal(String.valueOf(value)))
                .orElse(BigDecimal.ZERO);

        var expectedQuantity = Optional.ofNullable(tuple.get(3))
                .map(value -> new BigDecimal(String.valueOf(value)))
                .orElse(BigDecimal.ZERO);

        var actualQuantity = Optional.ofNullable(tuple.get(4))
                .map(value -> new BigDecimal(String.valueOf(value)))
                .orElse(BigDecimal.ZERO);

        return new InventoryReportItemDto(
            String.valueOf(tuple.get(0)),
            String.valueOf(tuple.get(1)),
            price,
            expectedQuantity,
            actualQuantity,
            price.multiply(expectedQuantity).setScale(2, RoundingMode.HALF_UP),
            price.multiply(actualQuantity).setScale(2, RoundingMode.HALF_UP)
        );
    }
}
