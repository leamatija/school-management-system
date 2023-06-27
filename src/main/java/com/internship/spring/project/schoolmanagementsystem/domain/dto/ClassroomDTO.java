package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomDTO {
    private Integer id;
    private String name;
    private Integer capacity;
    private Integer createdBy;
}
