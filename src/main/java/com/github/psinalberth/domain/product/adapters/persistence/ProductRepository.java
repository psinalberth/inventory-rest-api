package com.github.psinalberth.domain.product.adapters.persistence;

import com.github.psinalberth.domain.product.application.domain.model.Product;
import com.github.psinalberth.domain.product.application.domain.port.outgoing.LoadProductPort;
import com.github.psinalberth.domain.product.application.domain.port.outgoing.SaveProductPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, SaveProductPort, LoadProductPort {

    @Query("select p from Product p " +
            "join fetch p.category " +
            "join fetch p.group " +
            "where p.productId = :productId")
    Optional<Product> findByProductId(String productId);
}
