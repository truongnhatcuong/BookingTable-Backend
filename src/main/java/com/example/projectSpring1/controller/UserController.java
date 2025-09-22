package com.example.projectSpring1.controller;

import java.util.List;

import com.example.projectSpring1.dto.request.CustomerUpdateRequest;
import com.example.projectSpring1.dto.response.ApiResponse;
import com.example.projectSpring1.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        public ResponseEntity<ApiResponse<Page<CustomerResponse>>> getAllCustomers(@RequestParam(required = false) String firstName
            , @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "3") int size
            , @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "desc") String sortDir
                                                                                   ) {
        Page<CustomerResponse> customerResponse = customerService.getAllCustomer(firstName,page,size,sortBy, sortDir);
        return ResponseEntity.ok(
                new ApiResponse<>( "Success", customerResponse)
        );
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<CustomerResponse>> getIdForCustomer(@PathVariable Long id){
        CustomerResponse customer = customerService.getFindId(id);
        return ResponseEntity.ok(new ApiResponse<>("success",customer));
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
    

