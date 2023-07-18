package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import java.time.LocalDateTime;

public interface StudentReport {
    String getStudent();
    String getClassroom();
    String getSubject_name();
    String getTopic();
    LocalDateTime getSession_date();
    Boolean getPresence();
    Integer getParticipation();
    String getTeachers_notes();
    String getAssignment_name();
    Integer getAssignment_grade();
    String getAssignment_notes();

}
