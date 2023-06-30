package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomStudentsDTO;
import com.internship.spring.project.schoolmanagementsystem.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping()
    public ResponseEntity<ClassroomDTO> createClassroom(@RequestBody ClassroomDTO classroomDTO){
        return ResponseEntity.ok(classroomService.createClassroom(classroomDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDTO> updateClassroom(@PathVariable Integer id, @RequestBody ClassroomDTO classroomDTO){
        return ResponseEntity.ok(classroomService.updateClassroom(id,classroomDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClassroomDTO>> getAllClassrooms(){
        return ResponseEntity.ok(classroomService.getAllClassrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> getClassroomById (@PathVariable Integer id){
        return ResponseEntity.ok(classroomService.getClassroomById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom (@PathVariable Integer id){
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{classroomId}/students")
        public ResponseEntity<Void> addStudentsToClassroom(@PathVariable Integer classroomId, @RequestBody ClassroomStudentsDTO classroomStudentsDTO){
        classroomService.addStudentsToClassroom(classroomId,classroomStudentsDTO.getStudentsId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
