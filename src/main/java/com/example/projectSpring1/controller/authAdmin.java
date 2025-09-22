package com.example.projectSpring1.controller;


import com.example.projectSpring1.config.PasswordUtils;
import com.example.projectSpring1.dto.request.LoginRequest;
import com.example.projectSpring1.dto.response.ApiResponse;
import com.example.projectSpring1.dto.response.authenticationResponse;
import com.example.projectSpring1.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class authAdmin {
    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;
    @Autowired
      JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<authenticationResponse>> login(@RequestBody LoginRequest request) {

        if (adminUsername.equals(request.getUserName()) &&

                PasswordUtils.verifyPassword(request.getPassword(), adminPassword)) {
            String token = jwtUtil.generationToken(adminUsername);

            authenticationResponse authResponse = authenticationResponse.builder()
                    .token(token)
                    .authenticated(true)
                    .build();

            return ResponseEntity.ok(new ApiResponse<>("success", authResponse));
        }

        authenticationResponse authResponse = authenticationResponse.builder()
                .token(null)
                .authenticated(false)
                .build();

        return ResponseEntity.status(401).body(new ApiResponse<>("fail", authResponse));
    }

}
