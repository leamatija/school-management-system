package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ClassSessionService {

    ClassSessionDTO createClassSession(ClassSessionDTO classSessionDTO);
    ClassSessionDTO updateClassSession (Integer id, ClassSessionDTO classSessionDTO);
    void deleteClassSession (Integer id);
    List<ClassSessionDTO> getClassSessionsByClassroom(Integer classroomId);
    List<ClassSession> findAllByTeacherAndStartTimeInAndFinishTimeIn(User teacher, List<LocalDateTime> startLocalDateTimes, List<LocalDateTime> finishLocalDateTime);
    List<ClassSession> findAllByClassroomAndStartTimeInAndFinishTimeIn(Classroom classroom, List<LocalDateTime> startLocalDateTimes, List<LocalDateTime> finishLcalDateTime);





}
