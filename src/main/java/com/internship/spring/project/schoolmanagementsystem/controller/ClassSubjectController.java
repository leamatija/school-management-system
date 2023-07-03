package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSubjectDTO;
import com.internship.spring.project.schoolmanagementsystem.service.ClassSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class ClassSubjectController {

    private final ClassSubjectService classSubjectService;

    @PostMapping
    public ResponseEntity<ClassSubjectDTO> createSubject(@RequestBody ClassSubjectDTO req){
        return ResponseEntity.ok(classSubjectService.createSubject(req));
    }
}
