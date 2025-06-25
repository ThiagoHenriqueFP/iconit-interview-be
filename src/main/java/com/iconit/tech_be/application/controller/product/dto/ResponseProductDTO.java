package com.iconit.tech_be.application.controller.product.dto;

import com.iconit.tech_be.domain.enums.ProductType;
import com.iconit.tech_be.domain.product.Product;

public record ResponseProductDTO(
        String code,
        String description,
        ProductType type,
        Integer stockQuantity,
        Float totalValueSold,
        Integer totalAmountSold,
        Float profit
) {
    public static ResponseProductDTO from(Product product, Integer totalAmountSold, Float totalValueSold ) {
        return new ResponseProductDTO(
                product.getCode(),
                product.getDescription(),
                product.getProductType(),
                product.getStockQuantity(),
                totalValueSold,
                totalAmountSold,
                totalValueSold - (product.getSupplierPrice() * totalAmountSold)
                );
    }
}
