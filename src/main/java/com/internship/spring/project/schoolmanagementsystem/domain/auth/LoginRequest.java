package com.internship.spring.project.schoolmanagementsystem.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull
    @Email(message = "Email not valid")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
}
