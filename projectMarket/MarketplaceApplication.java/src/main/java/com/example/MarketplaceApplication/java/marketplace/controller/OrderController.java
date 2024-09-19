package com.example.MarketplaceApplication.java.marketplace.controller;

import com.example.MarketplaceApplication.java.marketplace.dto.OrderDTO;
import com.example.MarketplaceApplication.java.marketplace.entity.CartItem;
import com.example.MarketplaceApplication.java.marketplace.service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDto) {
        orderService.createOrder((List<CartItem>) orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }
}