package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AttendanceDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Attendance;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.AttendanceMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.AttendanceRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSessionRepository;
import com.internship.spring.project.schoolmanagementsystem.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor

public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ClassSessionRepository classSessionRepository;


    @Override
    public List<AttendanceDTO> getAttendance(Integer sessionId) {
        List<Attendance> attendanceList;
        var session = classSessionRepository.findById(sessionId).orElseThrow(()-> new ResourceNotFoundException(format(CLASS_SESSION_NOT_FOUND,sessionId)));
        if (session.getAttendances().size()>0){
            attendanceList = session.getAttendances();
        } else {
            attendanceList = session.getClassroom().getStudents().stream()
                    .map(s-> {
                        var attendance = new Attendance();
                        attendance.setUser(s);
                        attendance.setClassSession(session);
                        return attendance;
                    }).collect(Collectors.toList());
            attendanceList = attendanceRepository.saveAll(attendanceList);
        }
        return attendanceList.stream().map(AttendanceMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public List<AttendanceDTO> updateAttendance(Integer sessionId, List<AttendanceDTO> attendanceDTO) {

        var attendanceList = attendanceDTO.stream().map(a-> {
           var attendance = attendanceRepository.findById(a.getId()).orElseThrow(()-> new ResourceNotFoundException(format(ATTENDANCE_NOT_FOUND,a.getId())));
           attendance = AttendanceMapper.toUpdateEntity(attendance,a);
           return attendance;
        }).collect(Collectors.toList());
        return attendanceRepository.saveAll(attendanceList).stream().map(AttendanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AttendanceDTO findById(Integer id) {
        return attendanceRepository.findById(id).map(AttendanceMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(format(ATTENDANCE_NOT_FOUND,id)));
    }

    @Override
    public List<AttendanceDTO> findBySessionId(Integer sessionId) {
       var session = classSessionRepository.findById(sessionId).orElseThrow(()-> new ResourceNotFoundException(format(CLASS_SESSION_NOT_FOUND,sessionId)));
     return session.getAttendances().stream().map(AttendanceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAttendance(Integer id) {
        attendanceRepository.findById(id).ifPresentOrElse(a->{
                    a.setDeleted(true);
                    attendanceRepository.save(a);},
                ()-> new ResourceNotFoundException(format(ATTENDANCE_NOT_FOUND,id)));

    }
}
