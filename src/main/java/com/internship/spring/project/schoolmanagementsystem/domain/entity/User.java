package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted = false")
public class User extends BaseEntity<Integer> implements UserDetails {

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
    private boolean deleted=false;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Classroom> classrooms = new ArrayList<>();

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClassSession> classSessions=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attendance> attendances = new ArrayList<>();

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AssignmentResult> assignmentResults = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.getValue()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
