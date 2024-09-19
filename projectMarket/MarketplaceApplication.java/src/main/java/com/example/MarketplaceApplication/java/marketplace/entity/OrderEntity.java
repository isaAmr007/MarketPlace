package com.example.MarketplaceApplication.java.marketplace.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private double orderTotal;
    private String status;


    public OrderEntity() {
    }


    public OrderEntity(double orderTotal, String status) {
        this.orderTotal = orderTotal;
        this.status = status;
    }

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<CartItem> cartItems;

    private double totalPrice;

    // Метод для вычисления общей стоимости заказа
    public void calculateTotalPrice() {
        totalPrice = cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity)) return false;
        OrderEntity that = (OrderEntity) o;
        return id != null && id.equals(that.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", orderTotal=" + orderTotal +
                ", status='" + status + '\'' +
                '}';
    }

    public void setCartItems(List<CartItem> cartItems) {
    }
}
