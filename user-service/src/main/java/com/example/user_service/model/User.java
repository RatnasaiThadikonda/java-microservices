package com.example.user_service.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;


@Document(collection = "users")
@Data                       // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor           // Generates no-args constructor
@AllArgsConstructor          // Generates all-args constructor
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private Date createdAt;
    private Date updatedAt;
}