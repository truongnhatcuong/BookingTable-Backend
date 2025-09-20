package com.example.projectSpring1.controller;

import java.util.List;

import com.example.projectSpring1.dto.request.CustomerUpdateRequest;
import com.example.projectSpring1.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projectSpring1.dto.request.CustomerRequest;
import com.example.projectSpring1.dto.response.CustomerResponse;
import com.example.projectSpring1.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserController {

    @Autowired
      CustomerService customerService;
     
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.CreateUser(customerRequest);
        return ResponseEntity.status(201).body(
                new ApiResponse<>("success",response)
        );
        
    }
    
    @GetMapping()
        public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers() {
        List<CustomerResponse> customerResponse = customerService.getAllCustomer();
        return ResponseEntity.ok(
                new ApiResponse<>( "Success", customerResponse)
        );
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(@PathVariable Long id ,  @RequestBody CustomerUpdateRequest customerRequest){
        CustomerResponse updateNewCustomer = customerService.UpdateUser(id,customerRequest);
        return ResponseEntity.ok(
                new ApiResponse<>( "Success", updateNewCustomer)
        );
    }


    @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<Long>> deleteCustomer(@PathVariable Long id){
        Long deletedId = customerService.DeleteUser(id);   // 👈 use your service method
        return  ResponseEntity.ok(new ApiResponse<>("Delete Successfully",deletedId));
    }
    }
    

