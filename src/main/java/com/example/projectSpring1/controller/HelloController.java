 package com.example.projectSpring1.controller;

import com.example.projectSpring1.config.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController

public class HelloController {
    @Value("${app.admin.password}")
    private String adminPassword;

@GetMapping("/")
public String Hello() {
    return  "server is running";
}

    @GetMapping("/admin")
    public String Hello1() {


        boolean hashed = PasswordUtils.verifyPassword("admin123", adminPassword);
        System.out.println(hashed); // true ✅
        return  " kiem tra trung" +hashed;
    }

} 