package com.internship.spring.project.schoolmanagementsystem.domain.exception;

import lombok.Data;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
public class ExceptionConstants {

    public static final String USER_NOT_FOUND = "User with is %s is not found";
    public static final String CLASSROOM_NOT_FOUND = "Classroom with id %s is not found";
    public static final String CLASS_SESSION_NOT_FOUND = "Class session with id %s is not found";
    public static final String CLASS_SUBJECT_NOT_FOUND = "Class subject with id %s is not found";
    public static final String ATTENDANCE_NOT_FOUND = "Attendance with id %s is not found";
    public static final String ASSIGNMENT_NOT_FOUND = "Assignment with id %s is not found";
    public static final String EXAM_NOT_FOUND = "Exam with id %s is not found";
    public static final String TEACHER_CONSTRAINT_EXCEPTION = "Can not add teacher to this booked session because teacher with id %s is already booked";
    public static final String CLASSROOM_CONSTRAINT_EXCEPTION = "Classroom with id %s is booked, can not add sessions";
    public static final String CAPACITY_EXCEEDED = "Capacity exceeded";
    public static final String RESULT_NOT_FOUND = "Result with id %s is not found";


    public ExceptionConstants(HttpStatus httpStatus, Map<String,String> getRequiredFields){
    }




}
