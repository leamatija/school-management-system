package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDTO {

    private Integer id;
    @NotEmpty
    private Boolean present;
    private Integer participation;
    private String teachersNotes;
    private String studentName;


}
