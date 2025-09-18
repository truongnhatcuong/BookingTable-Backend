package com.example.projectSpring1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectSpring1.dto.request.CustomerRequest;
import com.example.projectSpring1.dto.response.CustomerResponse;
import com.example.projectSpring1.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserController {

    @Autowired
     final CustomerService customerService;
     
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.CreateUser(customerRequest);
        return ResponseEntity.ok(response);
        
    }
    
    @GetMapping()
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomer();
    }
    
}
