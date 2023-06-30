package com.internship.spring.project.schoolmanagementsystem.domain.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassroomSessionRequestDTO {
    private Integer teacherId;
    private Integer weekDay;
    private String startLocalDate;
    private String endLocalDate;
    private String startTime;
    private String endTime;

}
