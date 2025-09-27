package com.example.projectSpring1.controller;


import com.example.projectSpring1.dto.request.LoginRequest;
import com.example.projectSpring1.dto.response.ApiResponse;
import com.example.projectSpring1.dto.response.EmployeeResponse;
import com.example.projectSpring1.dto.response.AuthenticationResponse;
import com.example.projectSpring1.configuration.JwtUtil;
import com.example.projectSpring1.service.EmployeeUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAdmin {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final EmployeeUserDetailsService userDetailsService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            EmployeeResponse employeeResponse = userDetailsService.findByUsername(request.getUsername());

            return ResponseEntity.ok(
                    new ApiResponse<>("Login Success",
                            AuthenticationResponse.builder()
                                    .token(jwtUtil.generationToken(authentication.getName(),employeeResponse.getEmployeeId(),employeeResponse.getRole()))
                                    .authenticated(true)
                                    .build())
            );
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("Business Error",  AuthenticationResponse.builder()
                            .authenticated(false)
                            .token(null)
                            .error("User not found")
                            .build()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("Business Error",  AuthenticationResponse.builder()
                            .authenticated(false)
                            .token(null)
                            .error("Invalid password") // add extra field in AuthenticationResponse
                            .build()));
        }
    }
}
