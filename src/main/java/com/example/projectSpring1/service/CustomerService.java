package com.example.projectSpring1.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.projectSpring1.dto.request.CustomerUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.transaction.annotation.Transactional;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CustomerService  extends GenericService<Customer,Long> {
    @Autowired
     CustomerRepository customerRepository;
     CustomerMapper customerMapper;
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

    @Transactional(readOnly = true)
    public Page<CustomerResponse> getAllCustomer(String firstName, int page, int size,String sortBy, String sortDir) {
        Sort sort = "asc".equalsIgnoreCase(sortDir) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        int pageIndex = Math.max(page - 1, 0);
        Pageable pageable = PageRequest.of(pageIndex, size, sort);

        Page<Customer> customers = firstName != null && !firstName.isEmpty() ?
                customerRepository.findByFirstNameContaining(firstName,pageable)
                :    customerRepository.findAll(pageable);
        return customers.map(customerMapper::toCustomer);
    }

    public  CustomerResponse UpdateUser(Long id , CustomerUpdateRequest request){
        Customer existingCustomer =  customerRepository.findById(id).orElseThrow(()->  new RuntimeException("not found id " + id ));
    customerMapper.updateCustomerFromRequest(request,existingCustomer);
        Customer updated = customerRepository.save(existingCustomer);
    return  customerMapper.toCustomer(updated);
    }

        public  CustomerResponse getFindId(Long id){
            Customer customer = customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found id"));
            return customerMapper.toCustomer(customer);
        }

    public Long DeleteUser(Long id){
       customerRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"+id));
          customerRepository.deleteById(id);
        return id;
    }

 
}
