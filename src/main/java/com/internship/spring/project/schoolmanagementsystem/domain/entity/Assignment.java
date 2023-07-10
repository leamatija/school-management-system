package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "assignments")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted = false")
public class Assignment extends BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String fileName;
    private LocalDate expirationDate;
    private boolean deleted=false;

    @ManyToOne
    @JoinColumn(name = "session_id",referencedColumnName = "id")
    private ClassSession classSession;

}
