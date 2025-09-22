
 package com.example.projectSpring1.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projectSpring1.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Page<Customer> findByFirstNameContaining(String firstName, Pageable pageable);
    Optional<Customer> findByEmail(String email);
             Optional<Customer> findByPhone(String phone);
}