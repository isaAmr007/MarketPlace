package com.example.MarketplaceApplication.java.marketplace.service;

import com.example.MarketplaceApplication.java.marketplace.entity.CartItem;
import com.example.MarketplaceApplication.java.marketplace.entity.OrderEntity;
import com.example.MarketplaceApplication.java.marketplace.repository.OrderRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(List<CartItem> cartItems) {
        OrderEntity order = new OrderEntity() ;


        order.setCartItems(cartItems);
        order.calculateTotalPrice();
        return (Order) order;
    }
}
