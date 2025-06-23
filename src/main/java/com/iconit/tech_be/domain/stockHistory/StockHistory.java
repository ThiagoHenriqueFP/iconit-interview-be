package com.iconit.tech_be.domain.stockHistory;

import com.iconit.tech_be.domain.enums.Movement;
import com.iconit.tech_be.domain.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "stock_history")
public class StockHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    private Movement movement;
    private Float sellValue;
    private LocalDateTime sellDate;
    private Integer sellQuantity;
}
