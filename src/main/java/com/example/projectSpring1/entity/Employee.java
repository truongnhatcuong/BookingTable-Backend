package com.example.projectSpring1.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long employeeId;
    @Column(name = "employee_code", nullable = false, unique = true, length = 20)
     String employeeCode;
    @Column(name = "full_name", nullable = false, length = 100)
     String fullName;
    @Column(unique = true, length = 100)
     String email;
    @Column(length = 15)
     String phone;
    @Column(nullable = false, unique = true)
    String password;
    @Column(precision = 12, scale = 2)
     BigDecimal salary;
    @Column(name = "hire_date")
     LocalDate hireDate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('active', 'inactive', 'terminated') DEFAULT 'active'")
     EmployeeStatus status = EmployeeStatus.ACTIVE;
    enum EmployeeStatus {
        ACTIVE, INACTIVE, TERMINATED
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    Role role;

}
