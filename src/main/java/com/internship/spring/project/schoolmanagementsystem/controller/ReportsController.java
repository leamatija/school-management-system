package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AbsenceReport;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AverageReport;
import com.internship.spring.project.schoolmanagementsystem.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportsController {
    private final ReportsService reportsService;

    @GetMapping
    public ResponseEntity <List<AbsenceReport>> getAbsenceReportsForEveryStudent(){
        return ResponseEntity.ok(reportsService.getAbsencesForEveryStudent());
    }

    @GetMapping("/average")
    public ResponseEntity<List<AverageReport>> getAverageForEveryStudent(){
        return ResponseEntity.ok(reportsService.getAverageForEveryStudent());
    }



}
