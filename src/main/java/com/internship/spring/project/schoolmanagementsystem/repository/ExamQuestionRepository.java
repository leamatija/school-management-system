package com.internship.spring.project.schoolmanagementsystem.repository;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Integer> {
}
