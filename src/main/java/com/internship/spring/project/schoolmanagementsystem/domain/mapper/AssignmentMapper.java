package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentResultDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Assignment;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.AssignmentResult;

import java.time.LocalDate;

public class AssignmentMapper {

    public static AssignmentDTO toDto (Assignment a){
        return AssignmentDTO.builder()
                .id(a.getId())
                .expirationDate(a.getExpirationDate().toString())
                .fileName(a.getFileName())
                .name(a.getName())
                .build();
    }

    public static Assignment toEntity (AssignmentDTO a){
        return Assignment.builder()
                .id(a.getId())
                .expirationDate(LocalDate.parse(a.getExpirationDate()))
                .fileName(a.getFileName())
                .name(a.getName())
                .build();
    }

    public static AssignmentResultDTO toResultDTO (AssignmentResult ar){
        return AssignmentResultDTO.builder()
                .id(ar.getId())
                .teachersNotes(ar.getTeachersNotes())
                .grade(ar.getGrade())
                .assignmentName(ar.getAssignment().getName())
                .build();
    }

}
