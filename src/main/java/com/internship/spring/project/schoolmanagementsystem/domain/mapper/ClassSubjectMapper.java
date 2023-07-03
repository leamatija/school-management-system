package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSubjectDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSubject;

public class ClassSubjectMapper {

    public static ClassSubjectDTO toDto (ClassSubject c){
        return ClassSubjectDTO.builder()
                .id(c.getId())
                .subjectName(c.getSubject_name())
                .build();
    }

    public static ClassSubject toEntity(ClassSubjectDTO c){
        return ClassSubject.builder()
                .id(c.getId())
                .subject_name(c.getSubjectName())
                .build();
    }

}
