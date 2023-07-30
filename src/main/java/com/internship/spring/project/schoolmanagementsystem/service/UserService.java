package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ChangePasswordDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.PageDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.StudentReport;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.repository.specification.SearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO, String role);
    UserDTO updateUser (UserDTO updateDTO, Integer id);
    Void deleteUser (Integer id);
    UserDTO findUserById (Integer id);
    Page<UserDTO> findUserByRole (String role, PageDTO pageDTO);
    Page<UserDTO> filterUsers(List<SearchQuery> searchQueries, PageDTO pageDTO);
    void updatePassword(ChangePasswordDTO req);
    void forgotPassword(String email);
}
