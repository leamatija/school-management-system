package com.internship.spring.project.schoolmanagementsystem.repository;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.AssignmentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentResultRepository extends JpaRepository<AssignmentResult,Integer> {
}
