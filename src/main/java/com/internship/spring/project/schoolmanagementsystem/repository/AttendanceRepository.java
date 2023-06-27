package com.internship.spring.project.schoolmanagementsystem.repository;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
}
