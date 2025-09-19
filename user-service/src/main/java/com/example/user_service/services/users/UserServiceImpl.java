package com.example.user_service.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.user_service.dto.UserRequestDTO;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;

import java.util.Date;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "user-events";

    @Override
    public User createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhone(userRequestDTO.getPhone());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        User savedUser = userRepository.save(user);

        // Send Kafka message after saving
        sendUserCreatedMessage(savedUser);

        return savedUser;
    }

    @Override
    public void sendUserCreatedMessage(User user) {
        String message = "User created: " + user.getName() + " | Email: " + user.getEmail();
        kafkaTemplate.send(TOPIC, message);
    }
}