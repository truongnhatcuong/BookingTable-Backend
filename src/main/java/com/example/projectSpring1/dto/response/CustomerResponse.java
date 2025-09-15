package com.example.projectSpring1.dto.response;

import java.time.LocalDateTime;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerResponse {
          Long Id;
         String firstName;
         String lastName;
         String phone;
         String email;
         String notes;
         LocalDateTime createdAt;
}
