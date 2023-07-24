package com.internship.spring.project.schoolmanagementsystem.repository;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AbsenceReports;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.StudentReport;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {
    Page<User> findUserByRole(UserRole role, Pageable pageable);
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true,value = "select u.id as user_id,concat(u.first_name,' ',u.last_name) as student, cl.name as classroom,csub.subject_name ,ses.topic," +
            "ses.start_time as session_date,att.present as presence, att.participation, att.teachers_notes, a.name as assignment_name, ar.grade as assignment_grade, ar.teachers_notes as assingment_notes from users as u " +
            "inner join enrollment as en on en.student_id=u.id " +
            "inner join classrooms as cl on cl.id=en.classroom_id " +
            "inner join class_sessions as ses on ses.classroom_id=cl.id " +
            "inner join class_subjects as csub on ses.subject_id = csub.id " +
            "inner join attentance as att on att.session_id=ses.id and att.student_id=u.id " +
            "left join assignments a on ses.id = a.session_id " +
            "left join assignment_results ar on a.id = ar.assignment_id and ar.student_id=u.id " +
            "where u.id = ? and ses.start_time between ? and ?")
    List<StudentReport> getStudentReports(Integer studentId, LocalDateTime from, LocalDateTime to);


    @Query(nativeQuery = true, value = "select count(att.present)as Absences,u.id, concat(u.first_name,' ',u.last_name) as Student, c.name as Classroom from attentance att" +
            "inner join users u on att.student_id = u.id" +
            "inner join enrollment e on u.id = e.student_id" +
            "inner join classrooms c on c.id = e.classroom_id" +
            "where present = true" +
            "group by c.name ,u.id")
    List<AbsenceReports> getAbsencesForEveryStudent();
}
