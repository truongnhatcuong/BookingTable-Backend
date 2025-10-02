package com.example.projectSpring1.service;


import com.example.projectSpring1.GenericService.GenericService;
import com.example.projectSpring1.dto.request.RoleCreateRequest;
import com.example.projectSpring1.dto.response.RoleResponse;
import com.example.projectSpring1.entity.Permission;
import com.example.projectSpring1.entity.Role;
import com.example.projectSpring1.mapper.RoleMapper;
import com.example.projectSpring1.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service

public class RoleService extends GenericService<Role,Long> {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        super(roleRepository); // quan trọng: gán repository cho GenericService
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }
    public RoleResponse CreateRoleService(RoleCreateRequest request){
        Role role = roleMapper.toCreate(request);
        Role CreateRole =  roleRepository.save(role) ;
        return roleMapper.toRoleResponse(CreateRole);
    }
    @Transactional(readOnly = true)
    public List<RoleResponse> getAllRoles() {
       return roleMapper.toResponseList(roleRepository.findAll());
    }



    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
