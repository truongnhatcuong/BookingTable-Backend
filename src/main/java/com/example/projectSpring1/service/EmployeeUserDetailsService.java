package com.example.projectSpring1.service;

import com.example.projectSpring1.configuration.PasswordUtils;
import com.example.projectSpring1.dto.request.EmployeeRequest;
import com.example.projectSpring1.dto.request.EmployeeUpdateRequest;
import com.example.projectSpring1.dto.response.EmployeeResponse;
import com.example.projectSpring1.entity.Employee;
import com.example.projectSpring1.mapper.EmployeeMapper;
import com.example.projectSpring1.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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




    public Page<EmployeeResponse> getEmployeeService(String lastName , Pageable pageable){
        Page<Employee> employees = repo.findByLastNameContainingIgnoreCase(lastName,pageable);
        return  employees.map(employeeMapper::toResponse);
    }



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(employee.getUsername())
                .password(employee.getPassword()) // password must be BCrypt encoded
                .roles(employee.getRole().replace("ROLE_", ""))
                .build();
    }


// add employee
    public EmployeeResponse createEmployeeService(EmployeeRequest request){
        if(repo.existsByEmail(request.getEmail())){
            throw  new RuntimeException("email already exists !");
        }
        if(repo.existsByUsername(request.getUsername())){
            throw  new RuntimeException("username already exists !");
        }
        Employee employeeRequest = employeeMapper.toEmployee(request);
        employeeRequest.setPassword(PasswordUtils.hashPassword(employeeRequest.getPassword()));
        Employee saved = repo.save(employeeRequest);
        return  employeeMapper.toResponse(saved);
    }

    // find username
    public EmployeeResponse findByUsername(String username) {
        Employee employee = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return employeeMapper.toResponse(employee);
    }
        //getvalue
        public EmployeeResponse GetEmployeeDetail(Long id){
        Employee employee = repo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));
        return employeeMapper.toResponse(employee);
    }

    public EmployeeResponse updateEmployeeService(Long id, EmployeeUpdateRequest employee){
        Employee employeeExist = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found id"));
        employeeMapper.toEmployeeUpdate(employee, employeeExist);
        Employee updated = repo.save(employeeExist);
        return  employeeMapper.toResponse(updated);
    }

 public  EmployeeResponse DeleteEmployeeService (Long id){
     Employee employeeExist = repo.findById(id)
             .orElseThrow(() -> new RuntimeException("Not found id"));
         repo.deleteById(employeeExist.getEmployeeId());
     return employeeMapper.toResponse(employeeExist );
 }




}
