package com.example.projectSpring1.service;

import com.example.projectSpring1.config.PasswordUtils;
import com.example.projectSpring1.dto.request.EmployeeRequest;
import com.example.projectSpring1.dto.response.CustomerResponse;
import com.example.projectSpring1.dto.response.EmployeeResponse;
import com.example.projectSpring1.entity.Employee;
import com.example.projectSpring1.mapper.EmployeeMapper;
import com.example.projectSpring1.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {
    private final EmployeeRepository repo;
    private final EmployeeMapper employeeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(employee.getUsername())
                .password(employee.getPassword()) // password must be BCrypt encoded
                .roles(employee.getRole().replace("ROLE_", "")) // Strip ROLE_ if present since Spring adds it automatically
                .build();
    }

    public EmployeeResponse createEmployeeService(EmployeeRequest request){
        if(repo.existsByEmail(request.getEmail())){
            throw  new RuntimeException("email already exists !");
        }
        if(repo.existsByUsername(request.getUsername())){
            throw  new RuntimeException("email already exists !");
        }
        Employee employeeRequest = employeeMapper.toEmployee(request);
        employeeRequest.setPassword(PasswordUtils.hashPassword(employeeRequest.getPassword()));
        Employee saved = repo.save(employeeRequest);
        return  employeeMapper.toResponse(saved);
    }

    public EmployeeResponse findByUsername(String username) {
        Employee employee = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Map entity → DTO
        return employeeMapper.toResponse(employee);
    }




}
