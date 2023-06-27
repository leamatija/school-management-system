package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import java.util.Arrays;

@AllArgsConstructor
public enum UserRole {
    PRINCIPAL("PRINCIPAL"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    private String value;

    public static UserRole fromValue (String userRole){
        return Arrays.asList(UserRole.values()).stream().filter(r->r.value.equals(userRole))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException("Role not found"));
    }
    public String getValue(){return value;}
}
