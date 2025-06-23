package com.iconit.tech_be.application.controller.product.dto;

import com.iconit.tech_be.domain.enums.ProductType;
import com.iconit.tech_be.domain.product.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDTO(
        @NotBlank(message = "code is mandatory")
        String code,
        @NotBlank(message = "description is mandatory")
        String description,
        @NotNull(message = "type is mandatory")
        ProductType type,
        @Min(value = 0, message = "the supplier price must be greater than 0")
        Float supplierPrice,
        @Min(value = 1, message = "the in stock amount must be greater than 0")
        Integer stockQuantity
) {
    public Product toProduct() {
        Product product = new Product();
        product.setCode(code);
        product.setDescription(description);
        product.setProductType(type);
        product.setSupplierPrice(supplierPrice);
        product.setStockQuantity(stockQuantity);
        return product;
    }
}
