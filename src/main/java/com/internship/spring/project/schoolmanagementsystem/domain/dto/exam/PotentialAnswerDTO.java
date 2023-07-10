package com.internship.spring.project.schoolmanagementsystem.domain.dto.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PotentialAnswerDTO {

    private Integer id;
    private String value;
    private Boolean valid;
}
