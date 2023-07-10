package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "classrooms")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted = false")
public class Classroom extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer capacity;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active=true;
    private boolean deleted=false;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "enrollment",
            joinColumns = @JoinColumn(name = "classroom_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"))
    private List<User> students = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id",referencedColumnName = "id")
    private List<ClassSession> classSessions = new ArrayList<>();



















}
