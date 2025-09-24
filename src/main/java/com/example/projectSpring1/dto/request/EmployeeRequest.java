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
public class EmployeeRequest {
    @NotBlank(message = "First name is required")
    String firstName;

    @NotBlank(message = "Last name is required")
    String lastName;

    @Email(message = "Invalid email format")
    String email;
    @NotBlank(message = "Role is required")
    String role;
    @NotBlank(message = "Username is required")
    @Size(min = 4, message = "Username must be at least 4 characters")
    String username;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;
}
