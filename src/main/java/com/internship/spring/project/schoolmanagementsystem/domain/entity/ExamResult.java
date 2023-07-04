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
public class ExamResult extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer score;
    private Boolean passed;

    @OneToOne(mappedBy = "examResult")
    private Attendance attendance;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "exam_result_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "potential_answer_id",referencedColumnName = "id"))
    private List<PotentialAnswer> potentialAnswers = new ArrayList<>();
}
