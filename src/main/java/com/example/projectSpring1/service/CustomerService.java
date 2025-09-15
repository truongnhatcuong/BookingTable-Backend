package com.example.projectSpring1.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.projectSpring1.dto.request.CustomerRequest;
import com.example.projectSpring1.dto.response.CustomerResponse;
import com.example.projectSpring1.entity.Customer;
import com.example.projectSpring1.repository.CustomerRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CustomerService  {
    CustomerRepository customerRepository;
    public CustomerResponse CreateUser(CustomerRequest customerRequest){
            Customer customer = Customer.builder()
            .firstName(customerRequest.getFirstName())
            .lastName(customerRequest.getLastName())
            .phone(customerRequest.getPhone())
            .email(customerRequest.getEmail())
            .notes(customerRequest.getNotes())
            .createdAt(customerRequest.getCreatedAt() != null 
                        ? customerRequest.getCreatedAt() 
                        : LocalDateTime.now())
            .build();
            customer = customerRepository.save(customer);
             return CustomerResponse.builder()
                .Id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .notes(customer.getNotes())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
