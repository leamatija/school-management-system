package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassSessionDTO {
    private Integer id;
    @NotNull(message = "Topic should not be null")
    private String topic;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Integer createdBy;
    private Integer updatedBy;
    private Boolean cancelled;
}
