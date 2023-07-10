package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AttendanceDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    List<AttendanceDTO> getAttendance (Integer sessionId);
    List<AttendanceDTO> updateAttendance (Integer sessionId, List<AttendanceDTO> attendanceDTO);
    AttendanceDTO findById (Integer id);
    List<AttendanceDTO> findBySessionId(Integer sessionId);
    void deleteAttendance(Integer id);

}
