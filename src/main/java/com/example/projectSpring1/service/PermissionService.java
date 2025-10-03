package com.example.projectSpring1.service;

import com.example.projectSpring1.dto.response.PermissionResponse;
import com.example.projectSpring1.mapper.PermissionMapper;
import com.example.projectSpring1.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final  PermissionMapper permissionMapper;

    @Transactional(readOnly = true)
    public List<PermissionResponse> GetAllPermisionService(){
        return permissionMapper.toListPermission(permissionRepository.findAll());
    }

}
