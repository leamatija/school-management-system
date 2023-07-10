package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class ExamQuestion extends BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String value;

    @ManyToOne
    @JoinColumn(name = "exam_id",referencedColumnName = "id")
    private Exam exam;

    @OneToMany(mappedBy = "examQuestion",cascade = CascadeType.ALL)
    private List<PotentialAnswer> potentialAnswers = new ArrayList<>();

}
