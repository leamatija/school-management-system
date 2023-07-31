package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomSessionRequestDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.TimetableDTO;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

public interface ClassroomService {
    ClassroomDTO createClassroom (ClassroomDTO classroomDTO);
    ClassroomDTO updateClassroom(Integer id, ClassroomDTO classroomDTO);
    void deleteClassroom(Integer id);
    List<ClassroomDTO> getAllClassrooms();
    ClassroomDTO getClassroomById(Integer id);
    String addStudentsToClassroom (Integer classroomId, List<Integer> studentsId);
    void addClassSessionsToClass (Integer classroomId, @Valid ClassroomSessionRequestDTO req);
    List<TimetableDTO> getWeeklyTimetable(LocalDateTime start,LocalDateTime finish );
    List<TimetableDTO> getTeachersWeeklyTimetable(LocalDateTime start, LocalDateTime finish );
    void removeStudentFromClassroom(Integer classroomId,Integer studentsId);
}
