package com.example.MarketplaceApplication.java.marketplace.kafka;

import com.example.MarketplaceApplication.java.marketplace.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedMessage(OrderDTO orderDto, double totalPrice) {
        String message = String.format("Order created: %s, Total price: %.2f", orderDto, totalPrice);
        kafkaTemplate.send("order_created", message);
    }
}