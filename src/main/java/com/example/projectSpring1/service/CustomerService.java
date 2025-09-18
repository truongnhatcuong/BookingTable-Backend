package com.example.projectSpring1.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.projectSpring1.dto.request.CustomerRequest;
import com.example.projectSpring1.dto.response.CustomerResponse;
import com.example.projectSpring1.entity.Customer;
import com.example.projectSpring1.generics.GenericService;
import com.example.projectSpring1.mapper.CustomerMapper;
import com.example.projectSpring1.repository.CustomerRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CustomerService  extends GenericService<Customer,Long> {
    final CustomerRepository customerRepository;
    final CustomerMapper customerMapper;
   // 👇 Bắt buộc thêm constructor để gọi constructor cha
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        super(customerRepository); // gọi constructor GenericService
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
  
    public CustomerResponse CreateUser(CustomerRequest customerRequest){
           Customer customer = customerMapper.toCustomerEntity(customerRequest);
           if(customer.getCreatedAt() == null){
               customer.setCreatedAt(LocalDateTime.now());
           }
             // Check email exists
    if(customerRepository.findByEmail(customer.getEmail()).isPresent()){
        throw new RuntimeException("Email đã tồn tại: " + customer.getEmail());
    }
    // Check phone exists
    if(customerRepository.findByPhone(customer.getPhone()).isPresent()){
        throw new RuntimeException("Số điện thoại đã tồn tại: " + customer.getPhone());
    }


           Customer savedCustomer = customerRepository.save(customer);
           return customerMapper.toCustomer(savedCustomer);
    }

    public List<CustomerResponse> getAllCustomer(){
        
       return customerMapper.toCustomerList(customerRepository.findAll());
    }

    // public Customer findByEmail(String email){
    //     return customerRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Customer not found with email: " + email));
    // }
}
