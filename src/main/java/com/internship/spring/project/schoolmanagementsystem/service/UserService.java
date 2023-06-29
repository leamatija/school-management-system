package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO, String role);
    UserDTO updateUser (UserDTO updateDTO, Integer id);
    Void deleteUser (Integer id);
    UserDTO findUserById (Integer id);
    List<UserDTO> findUserByRole (String role);


}
