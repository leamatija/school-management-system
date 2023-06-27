package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSubjectDTO {
    private Integer id;
    private String subject_name;
    private Integer createdBy;
    private Integer updatedBy;

}
