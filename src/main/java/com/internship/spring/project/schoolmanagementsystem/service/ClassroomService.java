package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomSessionRequestDTO;

import javax.validation.Valid;
import java.util.List;

public interface ClassroomService {
    ClassroomDTO createClassroom (ClassroomDTO classroomDTO);
    ClassroomDTO updateClassroom(Integer id, ClassroomDTO classroomDTO);
    void deleteClassroom(Integer id);
    List<ClassroomDTO> getAllClassrooms();
    ClassroomDTO getClassroomById(Integer id);
    void addStudentsToClassroom (Integer classroomId, List<Integer> studentsId);
    void addClassSessionsToClass (Integer classroomId, @Valid ClassroomSessionRequestDTO req);
}
