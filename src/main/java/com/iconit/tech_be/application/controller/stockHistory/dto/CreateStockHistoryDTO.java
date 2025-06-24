package com.iconit.tech_be.application.controller.stockHistory.dto;

import com.iconit.tech_be.domain.enums.Movement;
import com.iconit.tech_be.domain.product.Product;
import com.iconit.tech_be.domain.stockHistory.StockHistory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateStockHistoryDTO(
        @NotNull(message = "product is mandatory")
        Product product,
        @NotNull(message = "movement is mandatory")
        Movement movement,
        @NotNull(message = "sell quantity is mandatory")
        @Min(value = 1, message = "sell quantity must be greater than zero")
        Integer sellQuantity,
        @NotNull(message = "sell value is mandatory")
        @Min(value = 1, message = "sell value must be greater than zero")
        Float sellValue
) {
        public StockHistory toStockHistory() {
                StockHistory history = new StockHistory();
                history.setProduct(product);
                history.setMovement(movement);
                history.setSellQuantity(sellQuantity);
                history.setSellValue(sellValue);
                history.setSellDate(LocalDateTime.now());
                history.setUpdatedAt(LocalDateTime.now());
                history.setCanceled(false);
                return history;
        }
}
