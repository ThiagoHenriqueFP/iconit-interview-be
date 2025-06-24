package com.iconit.tech_be.application.controller.stockHistoryProduct.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record InsertProductToStockHistoryDTO(
        @NotBlank(message = "code is mandatory")
        String code,
        @Min(value = 1, message = "stock amount must be greater than 0")
        @NotNull(message = "stock amount is mandatory")
        Integer stockAmount,
        Optional<Float> supplierPrice
        ) {
}
