package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "class_sessions")
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String topic;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Integer createdBy;
    private Integer updatedBy;
    private Boolean cancelled;

    @OneToOne(mappedBy = "classSession")
    private Exam exam;

    @OneToMany(mappedBy = "classSession")
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "classSession")
    private List<Attendance> attendances;

    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id",referencedColumnName = "id")
    private ClassSubject classSubject;

    @ManyToOne
    @JoinColumn(name = "classroom_id",referencedColumnName = "id")
    private Classroom classroom;


}
