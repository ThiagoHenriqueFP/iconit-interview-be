package com.iconit.tech_be.domain.stockHistory;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    Optional<StockHistory> findFirstByOrderByIdDesc();
    @Query("SELECT sh FROM stock_history sh where sh.isCanceled = false")
    List<StockHistory> findAllActive(Pageable pageable);

    @Query("SELECT sh FROM stock_history sh where sh.isCanceled = true")
    List<StockHistory> findAllInactive(Pageable pageable);

}
