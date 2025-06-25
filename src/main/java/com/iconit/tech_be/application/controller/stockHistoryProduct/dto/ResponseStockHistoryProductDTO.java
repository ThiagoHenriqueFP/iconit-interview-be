package com.iconit.tech_be.application.controller.stockHistoryProduct.dto;

import com.iconit.tech_be.domain.enums.Movement;
import com.iconit.tech_be.domain.stockHistory.StockHistory;

import java.time.LocalDateTime;

public record ResponseStockHistoryProductDTO(
        Long id,
        Movement movement,
        Float sellValue,
        String sellDate,
        Integer sellQuantity,
        Float totalValue,
        String productCode
) {
    public static ResponseStockHistoryProductDTO from (StockHistory stockHistory) {
        return new ResponseStockHistoryProductDTO(
                stockHistory.getId(),
                stockHistory.getMovement(),
                stockHistory.getSellValue(),
                stockHistory.getSellDate().toString(),
                stockHistory.getSellQuantity(),
                stockHistory.getTotalValue(),
                stockHistory.getProduct().getCode()
        );
    }
}
