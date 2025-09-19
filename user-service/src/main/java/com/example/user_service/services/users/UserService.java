package com.example.user_service.services.users;

import com.example.user_service.dto.UserRequestDTO;
import com.example.user_service.model.User;

public interface UserService {
    User createUser(UserRequestDTO userRequestDTO);

    void sendUserCreatedMessage(User user);
}