package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.configuration.TokenService;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomDTO;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomSessionRequestDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.TimetableDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ConstraintException;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.StorageException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.ClassroomMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSessionRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSubjectRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassroomRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import com.internship.spring.project.schoolmanagementsystem.service.ClassSessionService;
import com.internship.spring.project.schoolmanagementsystem.service.ClassroomService;
import com.internship.spring.project.schoolmanagementsystem.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.*;
import static java.lang.String.format;

@Validated
@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;
    private final ClassSubjectRepository classSubjectRepository;
    private final ClassSessionService classSessionService;


    @Override
    public ClassroomDTO createClassroom(ClassroomDTO classroomDTO) {
        return ClassroomMapper.toDto(classroomRepository.save(ClassroomMapper.toEntity(classroomDTO)));
    }

    @Override
    public ClassroomDTO updateClassroom(Integer id, ClassroomDTO classroomDTO) {
       return classroomRepository.findById(id)
                .map(c-> ClassroomMapper.toEntity(c,classroomDTO))
                .map(classroomRepository::save)
                .map(ClassroomMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(format(CLASSROOM_NOT_FOUND,id)));
    }

    @Override
    public void deleteClassroom(Integer id) {
        classroomRepository.findById(id)
                .ifPresentOrElse(c->{
                    c.setDeleted(true);
                    c.setActive(false);
                    classroomRepository.save(c);},
                        ()-> new ResourceNotFoundException(format(CLASSROOM_NOT_FOUND,id)));
    }

    @Override
    public List<ClassroomDTO> getAllClassrooms() {
        return classroomRepository.findAll().stream().map(ClassroomMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ClassroomDTO getClassroomById(Integer id) {
        return classroomRepository.findById(id)
                .map(ClassroomMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(format(CLASSROOM_NOT_FOUND,id)));
    }

    @Override
    public String addStudentsToClassroom(Integer classroomId,List<Integer> studentsId) {
    Classroom c = classroomRepository.findById(classroomId).orElseThrow(()-> new ResourceNotFoundException(format(CLASSROOM_NOT_FOUND,classroomId)));
    List<User> existedStudents = new ArrayList<>();
        if (studentsId.size()>c.getCapacity()){
            throw new StorageException(CAPACITY_EXCEEDED);
        }else {
            userRepository.findAllById(studentsId)
                    .forEach(u-> {
                        var existingStudent = classroomRepository.findAllByActiveAndStudents_id(true,u.getId());
                        if (existingStudent!=null&& existingStudent.size()>0){
                            existedStudents.add(u);
                        }else {
                            c.getStudents().add(u);
                        }
                    });
            classroomRepository.save(c);
        }

        String response = "OK";
        if(existedStudents.size()>0){
            var sb = new StringBuilder();
            sb.append("Users with ids: ");
            existedStudents.forEach(s -> sb.append(s.getId()).append(", "));
            sb.append(" cannot be added on classroom because they are added to another classroom");
            response = sb.toString();
        }
        return response;
    }



    @Transactional
    @Override
    public void addClassSessionsToClass(Integer classroomId, @Valid ClassroomSessionRequestDTO req) {
        var classroom = classroomRepository.findById(classroomId)
                .orElseThrow(()-> new ResourceNotFoundException(format(CLASSROOM_NOT_FOUND,classroomId)));

        var teacher = userRepository.findById(req.getTeacherId())
                .orElseThrow(()-> new ResourceNotFoundException(format(USER_NOT_FOUND,req.getTeacherId())));

        var sessionDates = DateUtils.generateDates(LocalDate.parse(req.getStartLocalDate()),LocalDate.parse(req.getEndLocalDate()),req.getWeekDays(), Optional.empty());
        var subject = classSubjectRepository.findById(req.getSubjectId()).orElseThrow(()-> new ResourceNotFoundException(format(CLASS_SUBJECT_NOT_FOUND,req.getSubjectId())));

        var generatedSessionsDate = sessionDates.stream()
                .map(d-> Pair.of(d.atTime(LocalTime.parse(req.getStartTime())),d.atTime(LocalTime.parse(req.getEndTime()))))
                .collect(Collectors.toList());

        var startDates = generatedSessionsDate.stream().map(p->p.getFirst()).collect(Collectors.toList());
        var endDates = generatedSessionsDate.stream().map(p->p.getSecond()).collect(Collectors.toList());

        var existingSession = classSessionService.findAllByTeacherAndStartTimeInAndFinishTimeIn(teacher,startDates,endDates);

        var existingClassroomSessions = classSessionService.findAllByClassroomAndStartTimeInAndFinishTimeIn(classroom,startDates,endDates);
        if (existingSession.size()>0){
            throw new ConstraintException(format(TEACHER_CONSTRAINT_EXCEPTION,req.getTeacherId()));
        }
        if (existingClassroomSessions.size()>0){
            throw new ConstraintException(format(CLASSROOM_CONSTRAINT_EXCEPTION,classroomId));
        }
        var generatedSessions = generatedSessionsDate.stream()
                .map(pair-> {
                    var session = new ClassSession();
                    session.setStartTime(pair.getFirst());
                    session.setFinishTime(pair.getSecond());
                    session.setClassroom(classroom);
                    session.setTeacher(teacher);
                    session.setClassSubject(subject);
                    return session;
                }).collect(Collectors.toList());
        classroom.setClassSessions(generatedSessions);
        classroomRepository.save(classroom);

    }

    @Override
    public List<TimetableDTO> getWeeklyTimetable(LocalDateTime start,LocalDateTime finish ) {
        return classroomRepository.getWeeklyTimetable(start,finish);
    }

    @Override
    public List<TimetableDTO> getTeachersWeeklyTimetable(LocalDateTime start, LocalDateTime finish) {
        Integer currentTeacherId = TokenService.getLoggedUser();
        return classroomRepository.getTeachersWeeklyTimetable(currentTeacherId,start,finish);
    }

    @Override
    public void removeStudentFromClassroom(Integer classroomId, Integer studentId) {
        Classroom c = classroomRepository.findById(classroomId).orElseThrow(()-> new ResourceNotFoundException(format(CLASSROOM_NOT_FOUND,classroomId)));
        List<User> students = c.getStudents().stream().filter(s-> s.getId()!= studentId).collect(Collectors.toList());
        c.setStudents(students);
        classroomRepository.save(c);
    }
}
