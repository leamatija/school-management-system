package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomAllDTO {
    private Integer id;
    private String name;
    private Integer capacity;
    private Integer createdBy;
    private List<User> students;
    private List<ClassSession> classSessions;

}
