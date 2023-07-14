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
@Table(name = "exam_results")
@EntityListeners(AuditingEntityListener.class)
public class ExamResult extends BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double score;
    private Boolean passed;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "class_session_id",referencedColumnName = "id")
    private ClassSession session;

    @ManyToMany
    @JoinTable(name = "exam_answers",joinColumns = @JoinColumn(name = "exam_result_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "potential_answer_id",referencedColumnName = "id"))
    private List<PotentialAnswer> potentialAnswers = new ArrayList<>();
}
