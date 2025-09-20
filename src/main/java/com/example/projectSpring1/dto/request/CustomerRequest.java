package com.example.projectSpring1.dto.request;



import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerRequest {
    @NotBlank(message = "first name khong duoc de trong")
    @Size(min = 2 , max = 50 , message = "vui long nhap first name it nhat 2 ki tu")
       String firstName;
    @NotBlank(message = "lastname khong duoc de trong")
    @Size(min = 2 , max = 50 , message = "vui long nhap last name it nhat 2 ki tu")
    String lastName;
          @NotBlank(message = "So dien thoai khong duoc de trong")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "So dien thoai phai tu 10-11 chu so")
         String phone;
         @NotBlank(message = "Email khong duoc de trong")
     @Email(message = "Email khong dung dinh dang")

         String email;
             @Size(max = 255, message = "Notes toi da 255 ky tu")

         String notes;
         LocalDateTime createdAt;
}
