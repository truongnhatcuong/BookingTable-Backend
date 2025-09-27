package com.example.projectSpring1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfo {
    private Long Id;
    private String username;
    private String role;

}
