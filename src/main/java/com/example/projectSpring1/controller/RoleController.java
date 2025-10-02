package com.example.projectSpring1.controller;

import com.example.projectSpring1.dto.request.RoleCreateRequest;
import com.example.projectSpring1.dto.response.ApiResponse;
import com.example.projectSpring1.dto.response.RoleResponse;
import com.example.projectSpring1.entity.Role;
import com.example.projectSpring1.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(@Valid @RequestBody RoleCreateRequest request){
        RoleResponse response = roleService.CreateRoleService(request);
        return ResponseEntity.ok(new ApiResponse<>("Create Success", response));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRoles() {
        List<RoleResponse> roles = roleService.getAllRoles();
        return ResponseEntity.ok(new ApiResponse<>("success",roles));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable Long id){
        roleService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse<>("Role deleted successfully", null));
    }
}
