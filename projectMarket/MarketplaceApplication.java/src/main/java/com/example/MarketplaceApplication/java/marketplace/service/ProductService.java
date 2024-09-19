package com.example.MarketplaceApplication.java.marketplace.service;

import com.example.MarketplaceApplication.java.marketplace.entity.ProductEntity;
import com.example.MarketplaceApplication.java.marketplace.exception.ProductNotFoundException;
import com.example.MarketplaceApplication.java.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<ProductEntity> findProductById(Long id) {
        return productRepository.findById(id);
    }
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }
}
