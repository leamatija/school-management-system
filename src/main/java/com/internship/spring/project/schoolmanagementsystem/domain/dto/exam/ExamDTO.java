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
public class ExamDTO {
    private Integer id;
    private String name;
    private Integer duration;
    List<ExamQuestionDTO> examQuestionDTOList;

}
