package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSessionDTO {
    private Integer id;
    @NotNull(message = "Topic should not be null")
    private String topic;
    private String startTime;
    private String finishTime;
    private String subject;
}
