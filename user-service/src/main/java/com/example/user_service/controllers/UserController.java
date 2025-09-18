package com.example.user_service.controllers;

import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
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
    public String createUser(@RequestBody Map<String, String> user) {
        kafkaTemplate.send("user-events", "User created: " + user.get("name"));
        return "User Created!";
    }
}
