package com.iconit.tech_be.application.controller.stockHistory;

import com.iconit.tech_be.application.controller.stockHistory.dto.CreateStockHistoryDTO;
import com.iconit.tech_be.application.controller.stockHistoryProduct.dto.ResponseStockHistoryProductDTO;
import com.iconit.tech_be.domain.stockHistory.StockHistory;
import com.iconit.tech_be.domain.stockHistory.StockHistoryService;
import com.iconit.tech_be.infrastructure.dto.DefaultResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock-histories")
public class StockHistoryController {
    private final StockHistoryService stockHistoryService;

    public StockHistoryController(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping()
    public ResponseEntity<DefaultResponseEntity<List<ResponseStockHistoryProductDTO>>> getStockHistory(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam Optional<Boolean> inactives
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), "id");
        Pageable pageable = PageRequest.of(pageNo, size, sort);
        List<ResponseStockHistoryProductDTO> dto = new ArrayList<>();
        if (inactives.isPresent())
            dto.addAll(this.stockHistoryService
                    .findByInactives(pageable)
                    .stream()
                    .map(ResponseStockHistoryProductDTO::from)
                    .toList());
        else
            dto.addAll(this.stockHistoryService
                    .findByActives(pageable)
                    .stream()
                    .map(ResponseStockHistoryProductDTO::from)
                    .toList());

        return ResponseEntity.ok(new DefaultResponseEntity<>(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponseEntity<StockHistory>> getStockHistoryById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(new DefaultResponseEntity<>(this.stockHistoryService.findById(id)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponseEntity<StockHistory>> updateStockHistory(
            @PathVariable("id") Long id,
            @RequestBody CreateStockHistoryDTO stockDTO
    ) {
        try {
            StockHistory stockHistory = stockDTO.toStockHistory();

            return ResponseEntity.ok(
                    new DefaultResponseEntity<>(
                            this.stockHistoryService.updateStockHistory(id, stockHistory)
                    )
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
