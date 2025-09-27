package com.example.projectSpring1.controller;

import com.example.projectSpring1.dto.request.EmployeeRequest;
import com.example.projectSpring1.dto.request.EmployeeUpdateRequest;
import com.example.projectSpring1.dto.response.ApiResponse;
import com.example.projectSpring1.dto.response.EmployeeResponse;

import com.example.projectSpring1.dto.response.PagedResponse;
import com.example.projectSpring1.entity.Employee;
import com.example.projectSpring1.service.EmployeeUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/detailt")
    public  ResponseEntity<ApiResponse<EmployeeResponse>> getDetailtEmployee(@AuthenticationPrincipal Jwt jwt){
        Long id = jwt.getClaim("id");
        EmployeeResponse employeeResponse = employeeUserDetailsService.GetEmployeeDetail(id);
        return  ResponseEntity.ok(new ApiResponse<>("Success",employeeResponse));
    }
    @GetMapping()
    public ResponseEntity<ApiResponse<PagedResponse<EmployeeResponse>>> getEmployees(
            @RequestParam(defaultValue = "")String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int limit
    ){
        Pageable pageable = PageRequest.of(page,limit);
        Page<EmployeeResponse> employees = employeeUserDetailsService.getEmployeeService(lastName,pageable);
        PagedResponse<EmployeeResponse> response = new PagedResponse<>(
                employees.getContent(),
                employees.getNumber(),
                employees.getSize(),
                employees.getTotalElements(),
                employees.getTotalPages()
        );
        return ResponseEntity.ok(new ApiResponse<>("success",response));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeUpdateRequest request){
        EmployeeResponse employeeUpdate = employeeUserDetailsService.updateEmployeeService(id,request);
        return ResponseEntity.ok(new ApiResponse<>("success",employeeUpdate));
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<ApiResponse<EmployeeResponse>> DeleteEmployeeService(@PathVariable Long id){
        EmployeeResponse deleteEmployee = employeeUserDetailsService.DeleteEmployeeService(id);
        return ResponseEntity.ok(new ApiResponse<>("Data is Deleted " +id ,deleteEmployee));
    }

}
