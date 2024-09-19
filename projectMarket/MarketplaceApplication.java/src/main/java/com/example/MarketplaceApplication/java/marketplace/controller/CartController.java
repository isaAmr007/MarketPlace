package com.example.MarketplaceApplication.java.marketplace.controller;

import com.example.MarketplaceApplication.java.marketplace.entity.CartEntity;
import com.example.MarketplaceApplication.java.marketplace.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartEntity cart = new CartEntity();
    private Map<Long, Product> productDatabase = new HashMap<>();

    public CartController() {
        // Инициализация продуктового каталога (БД)
        productDatabase.put(1L, new Product(1L, "Product 1", "Description 1", 10.0));
        productDatabase.put(2L, new Product(2L, "Product 2", "Description 2", 20.0));
    }

    @PostMapping("/add/{productId}")
    public String addProductToCart(@PathVariable Long productId) {
        Product product = productDatabase.get(productId);
        if (product != null) {
            cart.addProduct(product);
            return "Product " + product.getName() + " added to the cart.";
        } else {
            return "Product not found.";
        }
    }

    @GetMapping("/items")
    public List<Product> getCartItems() {
        return cart.getProducts();
    }

    @GetMapping("/total")
    public double getTotalPrice() {
        return cart.getTotalPrice();
    }
}
