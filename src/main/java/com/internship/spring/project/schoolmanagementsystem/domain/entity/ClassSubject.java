package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "class_subjects")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted = false")
public class ClassSubject extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject_name;
    private boolean deleted = false;

    @OneToMany(mappedBy = "classSubject")
    private List<ClassSession> classSessions = new ArrayList<>();

}
