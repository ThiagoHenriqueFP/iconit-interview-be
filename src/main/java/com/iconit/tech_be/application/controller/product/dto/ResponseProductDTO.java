package com.iconit.tech_be.application.controller.product.dto;

import com.iconit.tech_be.domain.enums.ProductType;
import com.iconit.tech_be.domain.product.Product;

public record ResponseProductDTO(
        String code,
        String description,
        ProductType type,
        Integer stockQuantity
) {
    public static ResponseProductDTO from(Product product) {
        return new ResponseProductDTO(
                product.getCode(),
                product.getDescription(),
                product.getProductType(),
                product.getStockQuantity()
                );
    }
}
