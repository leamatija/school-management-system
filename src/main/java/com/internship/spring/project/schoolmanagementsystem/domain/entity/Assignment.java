package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String fileName;
    private LocalDate expirationDate;
    private Boolean deleted;
    private Integer createdBy;
    private Integer updatedBy;

    @ManyToOne
    @JoinColumn(name = "session_id",referencedColumnName = "id")
    private ClassSession classSession;

}
