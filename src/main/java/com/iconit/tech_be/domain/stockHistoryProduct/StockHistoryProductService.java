package com.iconit.tech_be.domain.stockHistoryProduct;

import com.iconit.tech_be.domain.enums.Movement;
import com.iconit.tech_be.domain.product.Product;
import com.iconit.tech_be.domain.product.ProductService;
import com.iconit.tech_be.domain.stockHistory.StockHistory;
import com.iconit.tech_be.domain.stockHistory.StockHistoryRepository;
import com.iconit.tech_be.infrastructure.exceptions.customExceptions.CouldNotAcquireStockHistoryException;
import com.iconit.tech_be.infrastructure.exceptions.customExceptions.NotPersistedEntityException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockHistoryProduct {
    private final ProductService productService;
    private final StockHistoryRepository stockHistoryRepository;

    public StockHistoryProduct(ProductService productService, StockHistoryRepository stockHistoryRepository) {
        this.productService = productService;
        this.stockHistoryRepository = stockHistoryRepository;
    }

    @Transactional
    public boolean addProductToStock(String code, Integer stockAmount, Float sellValue) {
        Product product = productService.findByCode(code);
        product.setStockQuantity(product.getStockQuantity() + stockAmount);

        StockHistory stockHistory = new StockHistory();
        stockHistory.setProduct(product);
        stockHistory.setMovement(Movement.IN);
        stockHistory.setSellValue(sellValue);

        this.productService.update(code, product);
        this.stockHistoryRepository.save(stockHistory);

        return true;
    }

    @Transactional
    public boolean addProductToStock(String code, Integer stockAmount) {
        StockHistory history = this.stockHistoryRepository.findFirstByOrderByIdDesc().orElseThrow(
                () -> new CouldNotAcquireStockHistoryException(code)
        );
        return this.addProductToStock(code, stockAmount, history.getSellValue());

    }

    @Transactional
    public void removeProductsFromStock(String code, Integer amountToRemove, Float sellValue) {
        Product product = productService.findByCode(code);
        if (product.getStockQuantity() < amountToRemove)
            throw new IllegalArgumentException("Cannot remove more products that are not enough stock");

        product.setStockQuantity(product.getStockQuantity() - amountToRemove);

        StockHistory stockHistory = new StockHistory();
        stockHistory.setSellDate(LocalDateTime.now());
        stockHistory.setProduct(product);
        stockHistory.setMovement(Movement.OUT);
        stockHistory.setSellValue(sellValue);

        this.productService.update(code, product);
        this.stockHistoryRepository.save(stockHistory);
    }

    public void updateStockHistory(Long id, StockHistory stockHistory) {
        StockHistory history = this.stockHistoryRepository.findById(id).orElseThrow(
                () -> new NotPersistedEntityException(StockHistory.class, id)
        );


    }
}
