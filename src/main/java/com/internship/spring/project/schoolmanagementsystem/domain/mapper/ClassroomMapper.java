package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;

public class ClassroomMapper {

    public static ClassroomDTO toDto (Classroom c){
        return ClassroomDTO.builder()
                .name(c.getName())
                .capacity(c.getCapacity())
                .active(c.getActive())
                .startDate(c.getStartDate())
                .endDate(c.getEndDate())
                .build();
    }

    public static Classroom toEntity (ClassroomDTO c){
        return Classroom.builder()
                .name(c.getName())
                .capacity(c.getCapacity())
                .active(c.getActive())
                .startDate(c.getStartDate())
                .endDate(c.getEndDate())
                .build();
    }

    public static Classroom toEntity (Classroom c, ClassroomDTO cDTO){
        c.setName(cDTO.getName());
        c.setCapacity(cDTO.getCapacity());
        c.setActive(cDTO.getActive());
        c.setStartDate(cDTO.getStartDate());
        c.setEndDate(cDTO.getEndDate());
        c.setActive(cDTO.getActive());
        return c;
    }
}
