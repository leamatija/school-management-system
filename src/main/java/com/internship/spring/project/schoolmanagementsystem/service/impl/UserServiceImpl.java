package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.UserRole;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.UserMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import com.internship.spring.project.schoolmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO, String role) {
        User u = UserMapper.toEntity(userDTO);
        u.setRole(UserRole.fromValue(role));
        u.setPassword(userDTO.getPassword());
        return UserMapper.toDto(userRepository.save(u));
    }

    @Override
    public UserDTO updateUser(UserDTO updateDTO, Integer id) {
       return userRepository.findById(id)
                .map(u->UserMapper.toEntity(u,updateDTO))
                .map(userRepository::save)
                .map(UserMapper::toDto)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    @Override
    public Void deleteUser(Integer id) {
        userRepository.findById(id).ifPresentOrElse(u->{
            u.setDeleted(!u.getDeleted());
            userRepository.save(u);
            },()->new ResourceNotFoundException("User not found"));
        return null;
    }

    @Override
    public UserDTO findUserById(Integer id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserDTO> findUserByRole(String role) {
        return userRepository.findUserByRole(UserRole.fromValue(role)).stream()
                .map(UserMapper::toDto).collect(Collectors.toList());
    }
}
