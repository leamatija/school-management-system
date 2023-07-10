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
public class ExamQuestionDTO {
    private Integer id;
    private String value;
    List<PotentialAnswerDTO> potentialAnswerDTOS;
}
