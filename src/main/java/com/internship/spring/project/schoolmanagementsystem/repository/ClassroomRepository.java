package com.internship.spring.project.schoolmanagementsystem.repository;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.TimetableDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {
    List<Classroom> findAllByStartDateBetween (LocalDate start, LocalDate finish);

    @Query(nativeQuery = true, value = "select cr.name as classroom,csub.subject_name as subject, concat(u.first_name,' ',u.last_name  ) as teacher,cs.start_time as startTime, cs.finish_time as finishTime from  classrooms cr " +
            "inner join class_sessions cs on cr.id = cs.classroom_id inner join class_subjects csub on cs.subject_id = csub.id inner join users u on cs.teacher_id = u.id " +
            "where start_time between ? and ? ")
    List<TimetableDTO> getWeeklyTimetable(LocalDateTime start, LocalDateTime finish);

    @Query(nativeQuery = true, value = "select cr.name as classroom,csub.subject_name as subject, concat(u.first_name,' ',u.last_name  ) as teacher,cs.start_time as startTime, cs.finish_time as finishTime from  classrooms cr " +
            "inner join class_sessions cs on cr.id = cs.classroom_id inner join class_subjects csub on cs.subject_id = csub.id inner join users u on cs.teacher_id = u.id " +
            "where u.id = ? and start_time between ? and ?")
    List<TimetableDTO> getTeachersWeeklyTimetable( Integer teacherId,LocalDateTime start, LocalDateTime finish );

    List<Classroom> findAllByActiveAndStudents_id(boolean active, Integer studentId);
}

