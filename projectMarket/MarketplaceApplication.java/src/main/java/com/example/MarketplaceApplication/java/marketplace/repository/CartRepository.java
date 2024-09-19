package com.example.MarketplaceApplication.java.marketplace.repository;

import com.example.MarketplaceApplication.java.marketplace.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Product, Long> {

}
