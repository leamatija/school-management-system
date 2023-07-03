package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String parentContact;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
    private Boolean deleted=false;

    @ManyToMany
    @JoinTable(name = "enrollment",
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id",referencedColumnName = "id"))
    private List<Classroom> classrooms = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<ClassSession> classSessions=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Attendance> attendances = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<AssignmentResult> assignmentResults = new ArrayList<>();


}
