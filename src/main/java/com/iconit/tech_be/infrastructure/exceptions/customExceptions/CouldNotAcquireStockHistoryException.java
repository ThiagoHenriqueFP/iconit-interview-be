package com.iconit.tech_be.infrastructure.exceptions.customExceptions;

public class CouldNotAquireStockHistoryException extends RuntimeException {
    public CouldNotAquireStockHistoryException(String productCode) {
        super("The stock history could not be found for product: " + productCode);
    }
}
