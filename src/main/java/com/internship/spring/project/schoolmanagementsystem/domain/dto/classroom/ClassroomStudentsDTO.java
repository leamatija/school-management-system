package com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomStudentsDTO {

    private List<Integer> studentsId;
}
