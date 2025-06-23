package com.iconit.tech_be.application.controller.product;

import com.iconit.tech_be.application.controller.product.dto.CreateProductDTO;
import com.iconit.tech_be.application.controller.product.dto.ResponseProductDTO;
import com.iconit.tech_be.domain.product.Product;
import com.iconit.tech_be.domain.product.ProductService;
import com.iconit.tech_be.infrastructure.dto.DefaultResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            Product product = dto.toProduct();
            Product stored = this.productService.save(product);
            return new ResponseEntity<>(
                    new DefaultResponseEntity<>(ResponseProductDTO.from(stored)),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping
    ResponseEntity<DefaultResponseEntity<List<ResponseProductDTO>>> getAllProducts() {
        try {
            List<Product> products = this.productService.findAll();
            List<ResponseProductDTO> responseProductDTOS = products
                    .stream()
                    .map(ResponseProductDTO::from)
                    .toList();

            return ResponseEntity.ok(new DefaultResponseEntity<>(responseProductDTOS));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{code}")
    ResponseEntity<DefaultResponseEntity<ResponseProductDTO>> getProductByCode(@PathVariable String code) {
        try {
            Product product = this.productService.findByCode(code);
            return ResponseEntity.ok(new DefaultResponseEntity<>(ResponseProductDTO.from(product)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
