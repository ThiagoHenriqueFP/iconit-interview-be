package com.iconit.tech_be.domain.stockHistory;

import com.iconit.tech_be.domain.enums.ProductType;
import com.iconit.tech_be.infrastructure.exceptions.customExceptions.NotPersistedEntityException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockHistoryService {
    private final StockHistoryRepository stockHistoryRepository;

    public StockHistoryService(StockHistoryRepository stockHistoryRepository) {
        this.stockHistoryRepository = stockHistoryRepository;
    }

    public List<StockHistory> findAll() {
        return this.stockHistoryRepository.findAll();
    }

    public List<StockHistory> findByActives(Pageable pageable) {
        return this.stockHistoryRepository.findAllActive(pageable);
    }

    public List<StockHistory> findByInactives(Pageable pageable) {
        return this.stockHistoryRepository.findAllInactive(pageable);
    }

    public List<StockHistory> findByActivesByProductType(Pageable pageable, Integer type) {
        ProductType productType = ProductType.values()[type];
        return this.stockHistoryRepository.findByStockHistoriesByProductType(pageable, productType);
    }

    public StockHistory findById(Long id) {
        return stockHistoryRepository.findById(id).orElseThrow(
                () -> new NotPersistedEntityException(StockHistory.class, id)
        );
    }

    public StockHistory updateStockHistory(Long id, StockHistory stockHistory) {
        StockHistory history = this.stockHistoryRepository.findById(id).orElseThrow(
                () -> new NotPersistedEntityException(StockHistory.class, id)
        );

        history.setSellValue(stockHistory.getSellValue());
        history.setMovement(stockHistory.getMovement());
        history.setSellDate(stockHistory.getSellDate());
        history.setUpdatedAt(LocalDateTime.now());

        return this.stockHistoryRepository.save(history);
    }
}
