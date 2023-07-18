package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentRequest;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentResponse;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentResultDTO;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse createAssignment (Integer sessionId, AssignmentRequest a);
    AssignmentResponse findById (Integer id);
    List<AssignmentResponse> findAll();
    void deleteAssignment(Integer id);
    AssignmentResultDTO createAssignmentResult (Integer assignmentId, Integer studentId, AssignmentResultDTO req);
    AssignmentResultDTO findResultById (Integer resultId);
    List<AssignmentResultDTO> findResultByStudentId( Integer studentId);
    void deleteResult(Integer id);


}
