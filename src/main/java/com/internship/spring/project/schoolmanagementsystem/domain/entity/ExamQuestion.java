package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "exam_questions")
public class ExamQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;
    private Integer createdBy;
    private Integer updatedBy;

    @ManyToOne
    @JoinColumn(name = "exam_id",referencedColumnName = "id")
    private Exam exam;

    @OneToMany
    private List<PotentialAnswer> potentialAnswers = new ArrayList<>();

}
