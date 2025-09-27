 package com.example.projectSpring1.controller;

import com.example.projectSpring1.configuration.JwtUtil;
import com.example.projectSpring1.dto.response.JwtUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
public class ServerController {

@GetMapping("/")
public String Hello() {
    return  "server is running";
}
} 