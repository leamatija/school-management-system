package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer capacity;
    private Date createdAt;
    private Date updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;
    private Boolean deleted;

    @ManyToMany(mappedBy = "classrooms")
    private List<User> students = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "classroom_id",referencedColumnName = "id")
    private List<ClassSession> classSessions = new ArrayList<>();



















}
