package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.TopicRequestDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;

import java.time.LocalDateTime;

public class ClassSessionMapper {

    public static ClassSessionDTO toDto (ClassSession cs){
        return ClassSessionDTO.builder()
                .topic(cs.getTopic())
                .startTime(cs.getStartTime().toString())
                .finishTime(cs.getFinishTime().toString())
                .build();
    }

    public static ClassSession toEntity (ClassSessionDTO cs){
        return ClassSession.builder()
                .topic(cs.getTopic())
                .startTime(LocalDateTime.parse(cs.getStartTime()))
                .finishTime(LocalDateTime.parse(cs.getFinishTime()))
                .build();
    }

    public static ClassSession toEntity (ClassSession c, ClassSessionDTO cs){
        c.setTopic(cs.getTopic());
        c.setStartTime(LocalDateTime.parse(cs.getStartTime()));
        c.setFinishTime(LocalDateTime.parse(cs.getFinishTime()));
        return c;
    }



}
