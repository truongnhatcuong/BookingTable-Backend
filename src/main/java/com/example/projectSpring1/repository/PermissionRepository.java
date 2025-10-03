package com.example.projectSpring1.repository;

import com.example.projectSpring1.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {

}
