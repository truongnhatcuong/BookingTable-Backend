package com.example.projectSpring1.mapper;


import com.example.projectSpring1.dto.request.RoleCreateRequest;
import com.example.projectSpring1.dto.response.RoleResponse;
import com.example.projectSpring1.entity.Permission;
import com.example.projectSpring1.entity.Role;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toCreate(RoleCreateRequest request);
    @Mapping(target = "permissionNames", source = "permissions", qualifiedByName = "mapPermissions")
    RoleResponse toRoleResponse(Role role);
    @Mapping(target = "permissionNames", source = "permissions", qualifiedByName = "mapPermissions")
    List<RoleResponse> toResponseList(List<Role> roles);
    @Named("mapPermissions")
    @IterableMapping(qualifiedByName = "permissionNameMapper")
    default Set<String> mapPermissions(Set<Permission>  permissions){
        if(permissions ==null)return Set.of();
        return permissions.stream().map(Permission::getPermissionName).collect(Collectors.toSet());
    }
}
