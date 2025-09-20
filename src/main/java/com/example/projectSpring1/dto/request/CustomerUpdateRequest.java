package com.example.projectSpring1.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerUpdateRequest {
     String firstName;
     String lastName;
     String phone;
     String email;
     String notes;
}

