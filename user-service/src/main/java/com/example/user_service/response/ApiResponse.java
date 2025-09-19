package com.example.user_service.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor       // No-args constructor
@AllArgsConstructor      // All-args constructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
}
