package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamResultDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.exam.ExamSubmitDTO;
import com.internship.spring.project.schoolmanagementsystem.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam")
public class ExamController {

    private final ExamService examService;

    @PostMapping("/{sessionId}")
    public ResponseEntity <ExamDTO> createExam (@PathVariable Integer sessionId, @RequestBody ExamDTO examDTO){
        return ResponseEntity.ok(examService.createExam(sessionId,examDTO));
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<ExamDTO> takeExam(@PathVariable Integer sessionId){
        return ResponseEntity.ok(examService.takeExam(sessionId));
    }

    @PostMapping("/{sessionId}/submit")
    public ResponseEntity<ExamResultDTO> submitExam(@PathVariable Integer sessionId, @RequestBody ExamSubmitDTO req){
        return ResponseEntity.ok(examService.evaluateExam(req.getAnswers()));
    }
}
