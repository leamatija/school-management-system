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
public class QuestionResultDTO {

    private String question;
    private List<PotentialAnswerDTO> validAnswer;
    private List<PotentialAnswerDTO> selectedAnswer;
    private Boolean passed;
}
