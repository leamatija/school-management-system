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
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer duration;
    private Integer createdBy;
    private Integer updatedBy;

    @OneToOne
    @JoinColumn(name = "session_id",referencedColumnName = "id")
    private ClassSession classSession;

    @OneToMany(mappedBy = "exam")
    private List<ExamQuestion> examQuestions = new ArrayList<>();





}
