package com.iconit.tech_be.domain.stockHistoryProduct;

import com.iconit.tech_be.domain.enums.Movement;
import com.iconit.tech_be.domain.product.Product;
import com.iconit.tech_be.domain.product.ProductService;
import com.iconit.tech_be.domain.stockHistory.StockHistory;
import com.iconit.tech_be.domain.stockHistory.StockHistoryRepository;
import com.iconit.tech_be.infrastructure.exceptions.customExceptions.NotPersistedEntityException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockHistoryProductService {
    private final ProductService productService;
    private final StockHistoryRepository stockHistoryRepository;

    public StockHistoryProductService(ProductService productService, StockHistoryRepository stockHistoryRepository) {
        this.productService = productService;
        this.stockHistoryRepository = stockHistoryRepository;
    }

    @Transactional
    public StockHistory addProductToStock(String code, Integer stockAmount, Float supplierPrice) {
        Product product = productService.findEntityByCode(code);
        product.setStockQuantity(product.getStockQuantity() + stockAmount);
        product.setSupplierPrice(supplierPrice);

        StockHistory stockHistory = new StockHistory();
        stockHistory.setProduct(product);
        stockHistory.setMovement(Movement.IN);
        stockHistory.setSellValue(0f);
        stockHistory.setSellDate(LocalDateTime.now());

        this.productService.update(code, product);
        return this.stockHistoryRepository.save(stockHistory);
    }

    @Transactional
    public StockHistory addProductToStock(String code, Integer stockAmount) {
        Product product = productService.findEntityByCode(code);
        return this.addProductToStock(code, stockAmount, product.getSupplierPrice());
    }

    @Transactional
    public StockHistory removeProductsFromStock(String code, Integer amountToRemove, Float sellValue) {
        Product product = productService.findEntityByCode(code);
        if (product.getStockQuantity() < amountToRemove)
            throw new IllegalArgumentException("Cannot remove more products that are not enough stock");

        product.setStockQuantity(product.getStockQuantity() - amountToRemove);

        StockHistory stockHistory = new StockHistory();
        stockHistory.setSellDate(LocalDateTime.now());
        stockHistory.setProduct(product);
        stockHistory.setMovement(Movement.OUT);
        stockHistory.setSellQuantity(amountToRemove);
        stockHistory.setSellValue(sellValue);
        stockHistory.setTotalValue(sellValue * amountToRemove);

        this.productService.update(code, product);
        return this.stockHistoryRepository.save(stockHistory);
    }

    @Transactional
    public void deleteStockHistory(Long id) {
        StockHistory history = this.stockHistoryRepository.findById(id).orElseThrow(
                () -> new NotPersistedEntityException(StockHistory.class, id)
        );
        history.setCanceled(true);
        history.setUpdatedAt(LocalDateTime.now());
        Product product = history.getProduct();
        product.setStockQuantity(product.getStockQuantity() + history.getSellQuantity());

        this.productService.update(product.getCode(), product);
        this.stockHistoryRepository.save(history);
    }
}
