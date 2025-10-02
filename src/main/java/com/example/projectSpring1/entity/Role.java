package com.example.projectSpring1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long roleId;
    @Column(name = "role_name", nullable = false, unique = true, length = 50)
     String roleName;
    @Column(columnDefinition = "TEXT")
     String description;
    @OneToMany(mappedBy = "role")  // "role" là field trong Employee
    @JsonIgnore
    List<Employee> employees;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Permission> permissions;
}
