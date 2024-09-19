package com.example.MarketplaceApplication.java.marketplace.entity;

import java.util.ArrayList;
import java.util.List;

public class CartEntity {
    private List<Product> products;

    public CartEntity() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}
