package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomDTO {
    private Integer id;
    @NotEmpty(message = "This class must have a name")
    private String name;
    @NotEmpty(message = "Capacity can not be empty")
    private Integer capacity;
    @NotEmpty
    private String  startDate;
    @NotEmpty
    private String endDate;
    @NotEmpty
    private Boolean active;
}
