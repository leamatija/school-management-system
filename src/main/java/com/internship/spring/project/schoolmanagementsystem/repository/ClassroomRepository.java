package com.internship.spring.project.schoolmanagementsystem.repository;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {
    List<Classroom> findAllByStartDateBetween (LocalDate start, LocalDate finish);
}

