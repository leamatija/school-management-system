package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignment_results")
@EntityListeners(AuditingEntityListener.class)
public class AssignmentResult extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer grade;
    private String teachersNotes;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "assignment_id",referencedColumnName = "id")
    private Assignment assignment;


}
