package com.internship.spring.project.schoolmanagementsystem.domain.mapper;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;

public class ClassSessionMapper {

    public static ClassSessionDTO toDto (ClassSession cs){
        return ClassSessionDTO.builder()
                .topic(cs.getTopic())
                .startTime(cs.getStartTime())
                .finishTime(cs.getFinishTime())
                .build();
    }

    public static ClassSession toEntity (ClassSessionDTO cs){
        return ClassSession.builder()
                .topic(cs.getTopic())
                .startTime(cs.getStartTime())
                .finishTime(cs.getFinishTime())
                .build();
    }

    public static ClassSession toEntity (ClassSession c, ClassSessionDTO cs){
        c.setTopic(cs.getTopic());
        c.setStartTime(cs.getStartTime());
        c.setFinishTime(cs.getFinishTime());
        return c;
    }


}
