package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignment_results")
public class AssignmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer grade;
    private String teachersNotes;
    private Integer createdBy;
    private Integer updatedBy;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "assignment_id",referencedColumnName = "id")
    private Assignment assignment;


}
