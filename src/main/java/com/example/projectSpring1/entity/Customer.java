    package com.example.projectSpring1.entity;

    import java.time.LocalDateTime;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
    import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


    @Entity
    @Table(name = "customer")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)

    public class Customer {
    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
         Long id;
         String firstName;
         String lastName;
        @Column(nullable = false, unique = true)
         String phone;
        @Column(nullable = false, unique = true)
         String email;
         String notes;
        @Column(updatable = false)
         LocalDateTime createdAt;
    }
