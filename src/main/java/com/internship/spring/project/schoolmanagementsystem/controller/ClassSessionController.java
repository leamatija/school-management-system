package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.service.ClassSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classSessions")
public class ClassSessionController {

    private final ClassSessionService classSessionService;

    @PostMapping
    public ResponseEntity<ClassSessionDTO> createClassSession(@RequestBody ClassSessionDTO classSessionDTO){
        return ResponseEntity.ok(classSessionService.createClassSession(classSessionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassSessionDTO> updateClassSession(@PathVariable Integer id, @RequestBody ClassSessionDTO classSessionDTO){
        return ResponseEntity.ok(classSessionService.updateClassSession(id,classSessionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassSession (@PathVariable Integer id){
        classSessionService.deleteClassSession(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/classroomId}")
    public ResponseEntity<List<ClassSessionDTO>> getClassSessionsByClassroom (@PathVariable Integer classroomId){
        return ResponseEntity.ok(classSessionService.getClassSessionsByClassroom(classroomId));
    }



}
