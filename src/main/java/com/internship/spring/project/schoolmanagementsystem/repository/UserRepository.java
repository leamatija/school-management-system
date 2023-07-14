package com.internship.spring.project.schoolmanagementsystem.repository;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.PageDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {
    Page<User> findUserByRole(UserRole role, Pageable pageable);
    Optional<User> findByEmail(String email);

}
