package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
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
public class ClassSubject extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject_name;
    private Boolean deleted;

    @OneToMany(mappedBy = "classSubject")
    private List<ClassSession> classSessions = new ArrayList<>();

}
