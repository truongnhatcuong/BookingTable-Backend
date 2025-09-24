package com.example.projectSpring1.controller;

import com.example.projectSpring1.dto.request.EmployeeRequest;
import com.example.projectSpring1.dto.response.ApiResponse;
import com.example.projectSpring1.dto.response.EmployeeResponse;
import com.example.projectSpring1.service.EmployeeUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeUserDetailsService employeeUserDetailsService;
    @PostMapping()
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@Valid  @RequestBody EmployeeRequest request){
        EmployeeResponse employeeResponse = employeeUserDetailsService.createEmployeeService(request);
        return ResponseEntity.ok(new ApiResponse<>("Create Success",employeeResponse));
    }
}
