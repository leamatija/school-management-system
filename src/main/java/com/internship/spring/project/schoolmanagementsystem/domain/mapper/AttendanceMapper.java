package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AttendanceDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Attendance;

public class AttendanceMapper {

    public static AttendanceDTO toDto (Attendance a){
        return AttendanceDTO.builder()
                .id(a.getId())
                .participation(a.getParticipation())
                .present(a.getPresent())
                .teachersNotes(a.getTeachersNotes())
                .studentName(a.getUser().getFirstName()+" " + a.getUser().getLastName())
                .build();
    }

    public static Attendance toEntity (AttendanceDTO a){
        return Attendance.builder()
                .participation(a.getParticipation())
                .present(a.getPresent())
                .teachersNotes(a.getTeachersNotes())
                .build();
    }

    public static Attendance toUpdateEntity (Attendance attendance, AttendanceDTO attendanceDTO){
        attendance.setParticipation(attendanceDTO.getParticipation());
        attendance.setPresent(attendanceDTO.getPresent());
        attendance.setTeachersNotes(attendanceDTO.getTeachersNotes());
        return attendance;
    }
}
