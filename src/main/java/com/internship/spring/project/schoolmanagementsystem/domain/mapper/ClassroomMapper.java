package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;

import java.time.LocalDate;

public class ClassroomMapper {

    public static ClassroomDTO toDto (Classroom c){
        return ClassroomDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .capacity(c.getCapacity())
                .active(c.getActive())
                .startDate(c.getStartDate().toString())
                .endDate(c.getEndDate().toString())
                .build();
    }

    public static Classroom toEntity (ClassroomDTO c){
        return Classroom.builder()
                .name(c.getName())
                .capacity(c.getCapacity())
                .active(c.getActive())
                .startDate(LocalDate.parse(c.getStartDate()))
                .endDate(LocalDate.parse(c.getEndDate()))
                .build();
    }

    public static Classroom toEntity (Classroom c, ClassroomDTO cDTO){
        c.setName(cDTO.getName());
        c.setCapacity(cDTO.getCapacity());
        c.setActive(cDTO.getActive());
        c.setStartDate(LocalDate.parse(cDTO.getStartDate()));
        c.setEndDate(LocalDate.parse(cDTO.getEndDate()));
        c.setActive(cDTO.getActive());
        return c;
    }
}
