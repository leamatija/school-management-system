package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentResultDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.service.AssignmentService;
import com.internship.spring.project.schoolmanagementsystem.service.ClassSessionService;
import com.internship.spring.project.schoolmanagementsystem.service.impl.FileSystemStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classSessions")
public class ClassSessionController {

    private final ClassSessionService classSessionService;
    private final AssignmentService assignmentService;


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

    @GetMapping("/{classroomId}")
    public ResponseEntity<List<ClassSessionDTO>> getClassSessionsByClassroom (@PathVariable Integer classroomId){
        return ResponseEntity.ok(classSessionService.getClassSessionsByClassroom(classroomId));
    }

    @PostMapping("/{classSessionId}/assignment")
    public ResponseEntity<AssignmentDTO> createAssignment (@PathVariable Integer classSessionId, @ModelAttribute AssignmentDTO req ){
        return ResponseEntity.ok(assignmentService.createAssignment(classSessionId,req));
    }

    @GetMapping("/{classSessionId}/assignment/{assignmentId}")
    public ResponseEntity<AssignmentDTO> findAssignmentById (@PathVariable Integer classSessionId,@PathVariable Integer assignmentId){
        return ResponseEntity.ok(assignmentService.findById(assignmentId));
    }

    @GetMapping("/assignment/list")
    public ResponseEntity<List<AssignmentDTO>> findAllAssignments (){
        return ResponseEntity.ok(assignmentService.findAll());
    }

    @DeleteMapping("/assignment/{assignmentId}/delete")
    public ResponseEntity<Void> deleteAssignment (@PathVariable Integer assignmentId){
        assignmentService.deleteAssignment(assignmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assignment/{assignmentId}/result")
    public ResponseEntity<AssignmentResultDTO> createResult (@PathVariable Integer assignmentId,
                                                             @RequestParam Integer studentId, @RequestBody AssignmentResultDTO req){
        return ResponseEntity.ok(assignmentService.createAssignmentResult(assignmentId,studentId,req));
    }

    @GetMapping("/assignment/{assignmentId}/result/{resultId}")
    public ResponseEntity<AssignmentResultDTO> findResultById (@PathVariable Integer assignmentId, @PathVariable Integer resultId){
        return ResponseEntity.ok(assignmentService.findResultById(resultId));
    }

    @GetMapping("/assignment/{assignmentId}/result/student/{studentId}")
    public ResponseEntity<List<AssignmentResultDTO>> findResultsByStudent (@PathVariable Integer assignmentId, @PathVariable Integer studentId){
        return ResponseEntity.ok(assignmentService.findResultByStudentId(studentId));
    }

    @DeleteMapping("/assignment/{assignmentId}/result/{resultId}/delete")
    public ResponseEntity<Void> deleteResult (@PathVariable Integer assignmentId, @PathVariable Integer resultId ){
        assignmentService.deleteResult(resultId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
