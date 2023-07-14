package com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom;

import java.time.LocalDateTime;

public interface TimetableDTO {
    String getClassroom() ;
    String getSubject() ;
    String getTeacher();
    LocalDateTime getStartTime();
    LocalDateTime getFinishTime();
}
