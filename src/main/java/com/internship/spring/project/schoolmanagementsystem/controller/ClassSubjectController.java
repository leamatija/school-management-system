package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSubjectDTO;
import com.internship.spring.project.schoolmanagementsystem.service.ClassSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class ClassSubjectController {

    private final ClassSubjectService classSubjectService;

    @PostMapping
    public ResponseEntity<ClassSubjectDTO> createSubject(@RequestBody ClassSubjectDTO req){
        return ResponseEntity.ok(classSubjectService.createSubject(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassSubjectDTO> findById (@PathVariable Integer id){
        return ResponseEntity.ok(classSubjectService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClassSubjectDTO>> findAll(){
        return ResponseEntity.ok(classSubjectService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Integer id){
        classSubjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
