package com.example.projectSpring1.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long permissionId;
    @Column(name = "permission_name", nullable = false, unique = true, length = 100)
     String permissionName;
    @Column(columnDefinition = "TEXT")
     String description;
    @ManyToMany(mappedBy = "permissions")
     Set<Role> roles;
}
