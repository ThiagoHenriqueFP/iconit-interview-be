package com.iconit.tech_be.domain.product;

import com.iconit.tech_be.infrastructure.exceptions.customExceptions.AlreadyExistsException;
import com.iconit.tech_be.infrastructure.exceptions.customExceptions.NotPersistedEntityException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private boolean alreadyExists(Long productId) {
        return this.productRepository.findById(productId).isPresent();
    }

    private boolean alreadyExists(String code) {
        return this.productRepository.findProductByCode(code).isPresent();
    }


    public Product save(Product product) throws AlreadyExistsException {
        return productRepository.save(product);
    }

    public Product findByCode(String code) throws NotPersistedEntityException {
        return this.productRepository.findProductByCode(code).orElseThrow(
                () -> new NotPersistedEntityException(Product.class, code)
        );
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new NotPersistedEntityException(Product.class, id)
        );
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public void deleteById(Long id) {
        if (!alreadyExists(id))
            throw new NotPersistedEntityException(Product.class, id);

        this.productRepository.deleteById(id);

        if (alreadyExists(id))
            throw new RuntimeException("Product not deleted, please try again");
    }

    public void deleteByCode(String code) {
        if (!alreadyExists(code))
            throw new NotPersistedEntityException(Product.class, code);

        this.productRepository.deleteProductByCode(code);

        if (alreadyExists(code))
            throw new RuntimeException("Product not deleted, please try again");
    }

    public Product update(String code, Product product) {
        if (alreadyExists(code)) {
            Product oldProduct = this.findById(product.getId());
            product.setId(oldProduct.getId());
            product.setUpdatedAt(LocalDateTime.now());

            return this.productRepository.save(product);
        }

        throw new NotPersistedEntityException(Product.class, code);
    }
}
