package com.example.OrderService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "order_created", groupId = "order_service_group")
    public void listen(String message) {
        System.out.println("Received message: " + message);

        // Эмуляция работы отгрузки
        try {
            simulateShippingProcess(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем статус прерывания
            System.out.println("Shipping process was interrupted");
        }
    }

    private void simulateShippingProcess(String message) throws InterruptedException {
        System.out.println("Starting shipping process for order: " + message);

        // Эмуляция времени, необходимого для отгрузки (например, 5 секунд)
        Thread.sleep(5000);

        System.out.println("Shipping process completed for order: " + message);
    }
}
