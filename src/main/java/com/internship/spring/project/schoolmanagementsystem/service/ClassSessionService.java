package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;

import java.util.List;

public interface ClassSessionService {

    ClassSessionDTO createClassSession(ClassSessionDTO classSessionDTO);
    ClassSessionDTO addTeacherToClassSession (Integer id, UserDTO userDTO);
    ClassSessionDTO updateClassSession (Integer id, ClassSessionDTO classSessionDTO);
    Void deleteClassSession (Integer id);
    List<ClassSessionDTO> getClassSessionsByClassroom(Integer classroomId);





}
