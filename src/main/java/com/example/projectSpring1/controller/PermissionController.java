package com.example.projectSpring1.controller;

import com.example.projectSpring1.dto.response.ApiResponse;
import com.example.projectSpring1.dto.response.EmployeeResponse;
import com.example.projectSpring1.dto.response.PermissionResponse;
import com.example.projectSpring1.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/permission")
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionResponse>>> getALlPermisionWithRoles(){
        List<PermissionResponse> responses = permissionService.GetAllPermisionService();
            return ResponseEntity.ok(new ApiResponse<>("get Permission success",responses));
    }

}
