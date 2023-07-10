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
@Table(name = "potential_answers")
public class PotentialAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;
    private Boolean valid;

    @ManyToOne
    @JoinColumn(name = "question_id",referencedColumnName = "id")
    private ExamQuestion examQuestion;

    @ManyToMany(mappedBy = "potentialAnswers",cascade = CascadeType.ALL)
    private List<ExamResult> examResults = new ArrayList<>();

}
