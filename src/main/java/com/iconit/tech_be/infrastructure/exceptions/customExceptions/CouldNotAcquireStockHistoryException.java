package com.iconit.tech_be.infrastructure.exceptions.customExceptions;

public class CouldNotAcquireStockHistoryException extends RuntimeException {
    public CouldNotAcquireStockHistoryException(String productCode) {
        super("The stock history could not be found for product: " + productCode);
    }
}
