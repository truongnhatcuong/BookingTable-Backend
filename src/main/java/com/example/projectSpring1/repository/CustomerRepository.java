
 package com.example.projectSpring1.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projectSpring1.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
         Optional<Customer> findByEmail(String email);
             Optional<Customer> findByPhone(String phone);
}