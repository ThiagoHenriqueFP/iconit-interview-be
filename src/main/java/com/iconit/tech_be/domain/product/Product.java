package com.iconit.tech_be.domain.product;

import com.iconit.tech_be.domain.enums.ProductType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // code as string to adopt any pattern 'xd-123'
    @Column(nullable = false, unique = true)
    private String code;
    private String description;
    private ProductType productType;
    @Column(nullable = false)
    private Float supplierPrice;
    @Column(nullable = false)
    private Integer stockQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
