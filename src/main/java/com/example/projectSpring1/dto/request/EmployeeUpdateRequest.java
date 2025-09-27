package com.example.projectSpring1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeUpdateRequest {
    String firstName;
    String lastName;
    @Email(message = "Email must be valid")
    String email;
    String role;
    String username;
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;
}
