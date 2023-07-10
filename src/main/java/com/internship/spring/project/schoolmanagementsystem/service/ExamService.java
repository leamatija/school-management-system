package com.internship.spring.project.schoolmanagementsystem.service;


import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamResultDTO;

import java.util.List;

public interface ExamService {

    ExamDTO createExam (Integer sessionId ,ExamDTO examDTO);
    ExamDTO takeExam (Integer sessionId);
    ExamDTO findById (Integer id);
    ExamResultDTO evaluateExam(List<Integer> answers);
    void deleteExam (Integer id);




}
