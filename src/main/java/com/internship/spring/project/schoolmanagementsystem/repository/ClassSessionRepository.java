package com.internship.spring.project.schoolmanagementsystem.repository;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession,Integer> {
}
