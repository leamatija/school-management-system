package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AttendanceDTO;
import com.internship.spring.project.schoolmanagementsystem.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceOneController {

    private final AttendanceService attendanceService;


    @GetMapping("/session/{sessionId}")
    public ResponseEntity <List<AttendanceDTO>> getAttendance (@PathVariable Integer sessionId){
        return ResponseEntity.ok(attendanceService.getAttendance(sessionId));
    }

    @PutMapping("/{sessionId}")
    public ResponseEntity <List<AttendanceDTO>> updateAttendance (@PathVariable Integer sessionId, @RequestBody List<AttendanceDTO> req){
        return ResponseEntity.ok(attendanceService.updateAttendance(sessionId,req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDTO> findAttendanceById (@PathVariable Integer id){
        return ResponseEntity.ok(attendanceService.findById(id));
    }

    @GetMapping("/session/{sessionId}/list")
    public ResponseEntity<List<AttendanceDTO>> findBySessionId (@PathVariable Integer sessionId){
        return ResponseEntity.ok(attendanceService.findBySessionId(sessionId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance (@PathVariable Integer id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
