package com.iconit.tech_be.application.controller.product;

import com.iconit.tech_be.application.controller.product.dto.CreateProductDTO;
import com.iconit.tech_be.application.controller.product.dto.ResponseProductDTO;
import com.iconit.tech_be.domain.product.Product;
import com.iconit.tech_be.domain.product.ProductService;
import com.iconit.tech_be.domain.stockHistory.StockHistoryService;
import com.iconit.tech_be.infrastructure.dto.DefaultResponseEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    ResponseEntity<DefaultResponseEntity<ResponseProductDTO>> saveProduct(
            @Valid @RequestBody CreateProductDTO dto
    ) {

        Product product = dto.toProduct();
        Product stored = this.productService.save(product);
        return new ResponseEntity<>(
                new DefaultResponseEntity<>(ResponseProductDTO.from(stored, 0, 0f)),
                HttpStatus.CREATED);

    }

    @GetMapping
    ResponseEntity<DefaultResponseEntity<List<ResponseProductDTO>>> getAllProducts(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), "id");
        Pageable pageable = PageRequest.of(pageNo, size, sort);
        List<ResponseProductDTO> products = this.productService.findAll(pageable);

        return ResponseEntity.ok(new DefaultResponseEntity<>(products));

    }

    @GetMapping("/{code}")
    ResponseEntity<DefaultResponseEntity<ResponseProductDTO>> getProductByCode(@PathVariable String code) {
        ResponseProductDTO product = this.productService.findByCode(code);
        return ResponseEntity.ok(new DefaultResponseEntity<>(product));
    }

    @PutMapping("/{code}")
    ResponseEntity<?> updateProduct(
            @PathVariable String code,
            @Valid @RequestBody CreateProductDTO dto
    ) {
        return ResponseEntity.ok(new DefaultResponseEntity<>(this.productService.update(code, dto.toProduct())));
    }

    @DeleteMapping("/{code}")
    ResponseEntity<?> deleteProduct(@PathVariable String code) {
        this.productService.delete(code);
        return ResponseEntity.noContent().build();
    }

}
