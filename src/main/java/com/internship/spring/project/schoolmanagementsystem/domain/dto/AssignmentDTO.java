package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentDTO {
    private Integer id;
    @NotEmpty
    private String name;
    private String fileName;
    private String  expirationDate;
    private MultipartFile file;
}
