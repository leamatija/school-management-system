package com.internship.spring.project.schoolmanagementsystem.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamResultDTO {
    private Integer id;
    private Double score;
    private Boolean passed;
    private List<QuestionResultDTO> results;
}
