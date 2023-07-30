package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AbsenceReport;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AverageReport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportsService {

    void getStudentReports(LocalDateTime from, LocalDateTime to) throws IOException;
    List<AbsenceReport> getAbsencesForEveryStudent();
    List<AverageReport> getAverageForEveryStudent();


}
