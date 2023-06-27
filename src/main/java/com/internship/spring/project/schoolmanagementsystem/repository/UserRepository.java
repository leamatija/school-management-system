package com.internship.spring.project.schoolmanagementsystem.repository;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
