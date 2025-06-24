package com.iconit.tech_be.application.controller.stockHistoryProduct.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SellProductFromStockDTO(
        @NotBlank(message = "code is mandatory")
        String code,
        @Min(value = 1, message = "stock amount must be greater than 0")
        @NotNull(message = "sell amount is mandatory")
        Integer sellAmount,
        @Min(value = 1, message = "sell value must be greater than 0")
        @NotNull(message = "sell value is mandatory")
        Float sellValue
) {
}
