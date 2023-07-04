package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
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
public class Attendance extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean status;
    private Integer participation;
    private String teachersNotes;

    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "session_id",referencedColumnName = "id")
    private ClassSession classSession;

    @OneToOne
    @JoinColumn(name = "exam_result_id",referencedColumnName = "id")
    private ExamResult examResult;

}
