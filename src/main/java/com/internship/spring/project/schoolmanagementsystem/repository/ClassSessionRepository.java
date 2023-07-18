package com.internship.spring.project.schoolmanagementsystem.repository;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession,Integer> {
    ClassSession findFirstByIdAndTeacher_Id(Integer sessionId,Integer teacherId);
    List<ClassSession> findByClassroomId (Integer classroom_id);
    List<ClassSession> findAllByTeacherAndStartTimeInAndFinishTimeIn(User teacher, List<LocalDateTime> startLocalDateTimes, List<LocalDateTime> finishLcalDateTime);
    List<ClassSession> findAllByClassroomAndStartTimeInAndFinishTimeIn(Classroom classroom,List<LocalDateTime> startLocalDateTimes, List<LocalDateTime> finishLcalDateTime);
}
