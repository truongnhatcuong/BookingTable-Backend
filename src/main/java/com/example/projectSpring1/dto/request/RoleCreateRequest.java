package com.example.projectSpring1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleCreateRequest {
    @NotBlank(message = "Role name must not be empty")
    @Size(max = 50, message = "Role name must be at most 50 characters")
     String roleName;

    @Size(max = 255, message = "Description must be at most 255 characters")
     String description;
}
