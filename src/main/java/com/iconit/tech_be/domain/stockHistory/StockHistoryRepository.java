package com.iconit.tech_be.domain.stockHistory;

import com.iconit.tech_be.domain.enums.ProductType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    Optional<StockHistory> findFirstByOrderByIdDesc();
    @Query("SELECT sh FROM stock_history sh where sh.isCanceled = false")
    List<StockHistory> findAllActive(Pageable pageable);

    @Query("SELECT sh FROM stock_history sh where sh.isCanceled = true")
    List<StockHistory> findAllInactive(Pageable pageable);

    @Query("SELECT sh FROM stock_history sh where sh.product.productType = :type and sh.isCanceled = false order by sh.sellDate DESC")
    List<StockHistory> findByStockHistoriesByProductType(Pageable pageable, @Param("type") ProductType type);

}
