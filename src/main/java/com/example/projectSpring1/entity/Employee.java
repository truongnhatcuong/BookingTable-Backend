package com.example.projectSpring1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long employeeId;
     String firstName;
     String lastName;
    @Column(nullable = false, unique = true)   // ✅ unique username

    String email;
    @Column(nullable = false, unique = true)   // ✅ unique username

    String username;
    @Column(nullable = false)  // password not null
    String password;
    @Column(nullable = false)
    String role;
}
