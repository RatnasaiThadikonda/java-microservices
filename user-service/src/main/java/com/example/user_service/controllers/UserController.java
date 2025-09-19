package com.example.user_service.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping
    public String getUsers() {
        return "List of Users";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> user) {
        try {
            System.out.println("MMMMMMMMMMMMMM");
            // Send message and wait for ack
            SendResult<String, String> result = kafkaTemplate.send("user-events", "User created: " + user.get("name"))
                    .get(); // get() blocks
            System.out.println(result +"OOOOOOOOOOO");
            // Get RecordMetadata from SendResult
            RecordMetadata metadata = result.getRecordMetadata();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User Created!");
            response.put("topic", metadata.topic());
            response.put("partition", metadata.partition());
            response.put("offset", metadata.offset());
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error sending message to Kafka: " + e.getMessage());
        }
    }

}
