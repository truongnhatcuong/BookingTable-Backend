package com.example.projectSpring1.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class authenticationResponse {
    String token;
    boolean authenticated;
     String error; // optional error message

}
