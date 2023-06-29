package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;

public class UserMapper {
    public static UserDTO toDto(User u){
        return UserDTO.builder()
                .id(u.getId())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .parentContact(u.getParentContact())
                .build();
    }

    public static User toEntity(UserDTO u){
        return User.builder()
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .parentContact(u.getParentContact())
                .build();
    }

    public static User toEntity(User u, UserDTO userDTO){
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setEmail(userDTO.getEmail());
        u.setPhoneNumber(userDTO.getPhoneNumber());
        u.setParentContact(userDTO.getParentContact());
        return u;
    }
}
