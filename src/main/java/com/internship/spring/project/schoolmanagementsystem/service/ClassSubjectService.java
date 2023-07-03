package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSubjectDTO;

import java.util.List;

public interface ClassSubjectService {

    ClassSubjectDTO createSubject (ClassSubjectDTO classSubjectDTO);
    ClassSubjectDTO findById(Integer id);
    List<ClassSubjectDTO> findAll();
    void deleteSubject(Integer id);
}
