package com.iconit.tech_be.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsProductByCode(String code);
    Optional<Product> findProductByCode(String code);
    void deleteProductByCode(String code);
}
