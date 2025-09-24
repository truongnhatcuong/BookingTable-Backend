package com.example.projectSpring1.repository;

import com.example.projectSpring1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
