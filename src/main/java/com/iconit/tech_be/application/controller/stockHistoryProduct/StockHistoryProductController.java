package com.iconit.tech_be.application.controller.stockHistoryProduct;

import com.iconit.tech_be.application.controller.stockHistoryProduct.dto.InsertProductToStockHistoryDTO;
import com.iconit.tech_be.application.controller.stockHistoryProduct.dto.ResponseStockHistoryProductDTO;
import com.iconit.tech_be.application.controller.stockHistoryProduct.dto.SellProductFromStockDTO;
import com.iconit.tech_be.domain.stockHistory.StockHistory;
import com.iconit.tech_be.domain.stockHistoryProduct.StockHistoryProductService;
import com.iconit.tech_be.infrastructure.dto.DefaultResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequestMapping("/stock-history/products")
public class StockHistoryProductController {
    private final StockHistoryProductService stockHistoryProductService;

    public StockHistoryProductController(StockHistoryProductService stockHistoryProductService) {
        this.stockHistoryProductService = stockHistoryProductService;
    }

    @PostMapping()
    public ResponseEntity<?> addToStock(
            @Valid @RequestBody InsertProductToStockHistoryDTO dto
    ) {
        try {
            StockHistory stocked = dto.supplierPrice().isPresent()
                    ? this.stockHistoryProductService.addProductToStock(
                    dto.code(),
                    dto.stockAmount(),
                    dto.supplierPrice().get())
                    : this.stockHistoryProductService.addProductToStock(
                    dto.code(),
                    dto.stockAmount());

            return ResponseEntity.created(URI.create("/stock-history/products/" + stocked.getId())).build();
        } catch (Exception e) {
            // TODO test this to assign the correct throws
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    // TODO sell endpoint
    @PostMapping("/sell")
    public ResponseEntity<?> sellProduct(
            @Valid @RequestBody SellProductFromStockDTO dto
    ) {
        return ResponseEntity.ok(
                new DefaultResponseEntity<>(
                        ResponseStockHistoryProductDTO.from(this.stockHistoryProductService.removeProductsFromStock(
                                dto.code(),
                                dto.sellAmount(),
                                dto.sellValue()))
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStockHistory(@PathVariable Long id) {
            this.stockHistoryProductService.deleteStockHistory(id);

            return ResponseEntity.ok().build();
    }
}
