package com.example.email_service.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    @KafkaListener(topics = "user-events", groupId = "email-group")
    public void consume(String message) {
        System.out.println("Email Service received: " + message);
    }
}
