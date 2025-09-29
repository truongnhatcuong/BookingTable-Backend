package com.example.projectSpring1.service;


import com.example.projectSpring1.GenericService.GenericService;
import com.example.projectSpring1.dto.request.RoleCreateRequest;
import com.example.projectSpring1.dto.response.RoleResponse;
import com.example.projectSpring1.entity.Role;
import com.example.projectSpring1.mapper.RoleMaper;
import com.example.projectSpring1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class RoleService extends GenericService<Role,Long> {
    private final RoleRepository roleRepository;
    private final RoleMaper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMaper roleMapper) {
        super(roleRepository); // quan trọng: gán repository cho GenericService
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }
    public RoleResponse CreateRoleService(RoleCreateRequest request){
        Role role = roleMapper.toCreate(request);
        Role CreateRole =  roleRepository.save(role) ;
        return roleMapper.toRoleResponse(CreateRole);
    }


    public List<RoleResponse> findAllRoles() {
        return this.findAll().stream().map(roleMapper::toRoleResponse).toList();
        }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
