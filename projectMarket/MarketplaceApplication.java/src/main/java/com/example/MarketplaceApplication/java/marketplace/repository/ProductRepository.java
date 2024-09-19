package com.example.MarketplaceApplication.java.marketplace.repository;

import com.example.MarketplaceApplication.java.marketplace.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
