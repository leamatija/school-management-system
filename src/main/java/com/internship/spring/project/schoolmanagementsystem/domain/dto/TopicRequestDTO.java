package com.internship.spring.project.schoolmanagementsystem.domain.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicRequestDTO {

    @NotNull(message = "Topic should not be null")
    private String topic;
}
