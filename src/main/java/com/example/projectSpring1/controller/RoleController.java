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
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>>CreateRole(@Valid @RequestBody RoleCreateRequest request){
        RoleResponse response = roleService.CreateRoleService(request);
        return  ResponseEntity.ok(
                new ApiResponse<>("Create Success",response)
        );
    };

    @GetMapping
    public  ResponseEntity<ApiResponse<List<RoleResponse>>> getALlRole(){
        List<RoleResponse> roles = roleService.findAllRoles();
        return  ResponseEntity.ok(
                new ApiResponse<>("Create Success",roles)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> DeleteRole(@PathVariable Long id){
         roleService.deleteById(id);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("Role deleted successfully");
        response.setData(null);
        return  ResponseEntity.ok(response );
    }

}
