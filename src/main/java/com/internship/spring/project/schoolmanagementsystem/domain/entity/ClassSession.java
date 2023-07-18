package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "class_sessions")
@EntityListeners(AuditingEntityListener.class)
//@Where(clause = "deleted = false")
public class ClassSession extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String topic;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private boolean cancelled=false;
    private boolean deleted=false;


    @OneToMany(mappedBy = "classSession",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Assignment> assignments = new ArrayList<>();

    @OneToMany(mappedBy = "classSession",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendances = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id",referencedColumnName = "id")
    private ClassSubject classSubject;

    @ManyToOne
    @JoinColumn(name = "classroom_id",referencedColumnName = "id")
    private Classroom classroom;



}
