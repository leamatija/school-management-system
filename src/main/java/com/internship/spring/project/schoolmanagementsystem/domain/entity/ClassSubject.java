package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "class_subjects")
public class ClassSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject_name;
    private Boolean deleted;
    private Integer createdBy;
    private Integer updatedBy;

    @OneToMany(mappedBy = "classSubject")
    private List<ClassSession> classSessions = new ArrayList<>();

}
