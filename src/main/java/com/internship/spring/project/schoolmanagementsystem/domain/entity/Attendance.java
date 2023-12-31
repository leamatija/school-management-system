package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "attentance")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted = false")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean present;
    private Integer participation;
    private String teachersNotes;
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "session_id",referencedColumnName = "id")
    private ClassSession classSession;


}
