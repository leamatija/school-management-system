package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.configuration.TokenService;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.ChangePasswordDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.PageDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.UserRole;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ConstraintException;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.UserMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.specification.SearchQuery;
import com.internship.spring.project.schoolmanagementsystem.repository.specification.UserSpecification;
import com.internship.spring.project.schoolmanagementsystem.service.EmailService;
import com.internship.spring.project.schoolmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.USER_NOT_FOUND;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    @Override
    public UserDTO createUser(UserDTO userDTO, String role){
        User u = UserMapper.toEntity(userDTO);
        u.setRole(UserRole.fromValue(role));
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return UserMapper.toDto(userRepository.save(u));
    }

    @Override
    public UserDTO updateUser(UserDTO updateDTO, Integer id) {
       return userRepository.findById(id)
                .map(u->UserMapper.toEntity(u,updateDTO))
                .map(userRepository::save)
                .map(UserMapper::toDto)
                .orElseThrow(()->new ResourceNotFoundException(format(USER_NOT_FOUND,id)));
    }

    @Override
    public Void deleteUser(Integer id) {
        userRepository.findById(id).ifPresentOrElse(u->{
            u.setDeleted(true);
            userRepository.save(u);
            },()->new ResourceNotFoundException(format(USER_NOT_FOUND,id)));
        return null;
    }

    @Override
    public UserDTO findUserById(Integer id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(format(USER_NOT_FOUND,id)));
    }

    @Override
    public Page<UserDTO> findUserByRole(String role, PageDTO pageDTO) {
        Sort sort = getSort(pageDTO);
        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(),
                pageDTO.getPageSize(),sort);
        return userRepository.findUserByRole(UserRole.fromValue(role),pageable).map(UserMapper::toDto);
    }

    @Override
    public Page<UserDTO> filterUsers(List<SearchQuery> searchQueries, PageDTO pageDTO) {
        Sort sort = getSort(pageDTO);

        Pageable pageable = PageRequest.of(pageDTO.getPageNumber(),
                pageDTO.getPageSize(),sort);
        if(searchQueries!=null && searchQueries.size()>0){
            var userSpec = UserSpecification.toSpecification(searchQueries);
            return userRepository.findAll(userSpec,pageable).map(UserMapper::toDto);
        }else {
            return userRepository.findAll(pageable).map(UserMapper::toDto);
        }
    }

    @Override
    public void updatePassword(ChangePasswordDTO req) {
        if(!req.getPassword().equals(req.getConfirmPassword())){
            throw new ConstraintException("Password and confirm password did not match");
        }
        var userId = TokenService.getLoggedUser();
        userRepository.findById(userId)
                .map(u->{
                    u.setPassword(passwordEncoder.encode(req.getPassword()));
                    return userRepository.save(u);
                }).orElseThrow(()-> new ResourceNotFoundException(String.format(USER_NOT_FOUND,userId)));
    }

    @Transactional
    @Override
    public void forgotPassword(String email) {
        String password = "passwordToChange";
        userRepository.findByEmail(email)
                .ifPresentOrElse(u-> {
                    u.setPassword(passwordEncoder.encode(password));
                    userRepository.save(u);
                    String message = String.format("Password is: %s ",password);
                    String subject = "Password updated";
                    emailService.sendSimpleMailMessage(email,subject,message);
                },()-> new ResourceNotFoundException(String.format("User with email %s does not exist",email)));



    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(format("User with email %s not found",email)
                ));
    }

    private Sort getSort(PageDTO p){
        if(p.getSortDirection().equals("ASC")){
            return Sort.by(Sort.Direction.ASC, p.getSortBy());
        }else {
            return Sort.by(Sort.Direction.DESC, p.getSortBy());
        }
    }
}

