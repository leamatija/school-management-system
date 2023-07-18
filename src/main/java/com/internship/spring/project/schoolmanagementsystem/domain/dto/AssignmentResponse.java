package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentResponse {
    private Integer id;
    private String name;
    private String fileName;
    private String  expirationDate;
    private String path;
}
