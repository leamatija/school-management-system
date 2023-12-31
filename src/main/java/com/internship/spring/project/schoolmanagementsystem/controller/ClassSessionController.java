package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.*;
import com.internship.spring.project.schoolmanagementsystem.service.AssignmentService;
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
    private final AssignmentService assignmentService;


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
    public ResponseEntity<AssignmentResponse> createAssignment (@PathVariable Integer classSessionId, @ModelAttribute AssignmentRequest req ){
        return ResponseEntity.ok(assignmentService.createAssignment(classSessionId,req));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<AssignmentResponse> findAssignmentById (@PathVariable Integer assignmentId){
        return ResponseEntity.ok(assignmentService.findById(assignmentId));
    }

    @GetMapping("/assignment/list")
    public ResponseEntity<List<AssignmentResponse>> findAllAssignments (){
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

    @GetMapping("/assignment/result/student/{studentId}")
    public ResponseEntity<List<AssignmentResultDTO>> findResultsByStudent (@PathVariable Integer studentId){
        return ResponseEntity.ok(assignmentService.findResultByStudentId(studentId));
    }

    @DeleteMapping("/assignment/result/{resultId}/delete")
    public ResponseEntity<Void> deleteResult (@PathVariable Integer resultId ){
        assignmentService.deleteResult(resultId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<Void> setCancellation (@PathVariable Integer sessionId, @RequestParam Boolean cancel){
        classSessionService.setCancellation(sessionId,cancel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{sessionId}/topic")
    public ResponseEntity<Void> addTopic (@PathVariable Integer sessionId, @RequestBody TopicRequestDTO topicReq){
        classSessionService.addTopic(sessionId,topicReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
