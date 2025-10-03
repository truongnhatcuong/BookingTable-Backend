package com.example.projectSpring1.mapper;

import com.example.projectSpring1.dto.response.PermissionResponse;
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
public interface PermissionMapper {
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNames")
    @Mapping(source = "permissionId" , target = "id")
    @Mapping(source = "permissionName" , target = "name")
    PermissionResponse toPermissionResponse(Permission permission);
    List<PermissionResponse> toListPermission(List<Permission> permissions);
    @Named("roleNames")
    @IterableMapping(qualifiedByName = "roleNames")
         default Set<String> mapRoles(Set<Role> roles){
        if(roles==null) return Set.of();
        return roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
    }

}
