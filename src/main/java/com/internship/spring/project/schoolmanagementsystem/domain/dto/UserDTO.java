package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.UserRole;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    @NotNull(message = "Firstname is required")
    @NotEmpty(message = "This field can not be empty")
    private String firstName;
    @NotNull(message = "Lastname is required")
    @NotEmpty(message = "This field can not be empty")
    private String lastName;
    @Email(message = "Email is not valid")
    private String email;
    private String phoneNumber;
    private String parentContact;
    @NotNull(message = "Password is required")
    private String password;


}
