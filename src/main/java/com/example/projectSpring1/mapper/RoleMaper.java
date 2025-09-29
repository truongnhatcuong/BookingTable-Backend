package com.example.projectSpring1.mapper;


import com.example.projectSpring1.dto.request.RoleCreateRequest;
import com.example.projectSpring1.dto.response.RoleResponse;
import com.example.projectSpring1.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMaper {

    @Mapping(source = "roleName" , target = "roleName")
    Role toCreate(RoleCreateRequest request);
    @Mapping(source = "roleId", target = "id")
    @Mapping(source = "roleName" , target = "name")
    RoleResponse toRoleResponse(Role role);
}
