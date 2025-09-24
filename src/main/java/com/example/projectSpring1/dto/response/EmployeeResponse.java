    package com.example.projectSpring1.dto.response;

    import lombok.*;
    import lombok.experimental.FieldDefaults;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class EmployeeResponse {
        Long employeeId;
        String firstName;
        String lastName;
        String email;
        String role;
        String username;
    }
