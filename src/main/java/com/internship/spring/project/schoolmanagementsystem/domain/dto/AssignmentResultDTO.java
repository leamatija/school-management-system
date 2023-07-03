package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentResultDTO {

    private Integer id;
    private Integer grade;
    private String teachersNotes;
    private String assignmentName;
}
