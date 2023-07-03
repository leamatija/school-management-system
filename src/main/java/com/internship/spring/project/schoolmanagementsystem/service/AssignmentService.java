package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentResultDTO;

import java.util.List;

public interface AssignmentService {

    AssignmentDTO createAssignment (Integer sessionId, AssignmentDTO a);
    AssignmentDTO findById (Integer id);
    List<AssignmentDTO> findAll();
    void deleteAssignment(Integer id);
    AssignmentResultDTO createAssignmentResult (Integer assignmentId, Integer studentId, AssignmentResultDTO req);

}
