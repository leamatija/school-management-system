package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomDTO;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomSessionRequestDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ConstraintException;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Classroom with id %s not found",id)));
    }

    @Override
    public void deleteClassroom(Integer id) {
        classroomRepository.findById(id)
                .ifPresentOrElse(c->{
                    c.setDeleted(!c.getDeleted());
                    classroomRepository.save(c);},
                        ()-> new ResourceNotFoundException(String.format("Classroom with id %s not found",id)));
    }

    @Override
    public List<ClassroomDTO> getAllClassrooms() {
        return classroomRepository.findAll().stream().map(ClassroomMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ClassroomDTO getClassroomById(Integer id) {
        return classroomRepository.findById(id)
                .map(ClassroomMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Classroom with id %s not found",id)));
    }

    @Override
    public void addStudentsToClassroom(Integer classroomId,List<Integer> studentsId) {
    Classroom c = classroomRepository.findById(classroomId).orElseThrow(()-> new ResourceNotFoundException(String.format("Classroom with id %s not found",classroomId)));
        if (studentsId.size()>c.getCapacity()){
            throw new RuntimeException("Capacity exceeded");
        }else {
            List<User> students = userRepository.findAllById(studentsId);
            c.setStudents(students);
            classroomRepository.save(c);
        }
    }



    @Transactional
    @Override
    public void addClassSessionsToClass(Integer classroomId, @Valid ClassroomSessionRequestDTO req) {
        var classroom = classroomRepository.findById(classroomId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Classroom with id %s not found",classroomId)));

        var teacher = userRepository.findById(req.getTeacherId())
                .orElseThrow(()-> new ResourceNotFoundException(String.format("User with id %s not found",req.getTeacherId())));

        var sessionDates = DateUtils.generateDates(LocalDate.parse(req.getStartLocalDate()),LocalDate.parse(req.getEndLocalDate()),req.getWeekDays(), Optional.empty());
        var subject = classSubjectRepository.findById(req.getSubjectId()).orElseThrow(()-> new ResourceNotFoundException(String.format("Subject with id %s not found",req.getSubjectId())));

        var generatedSessionsDate = sessionDates.stream()
                .map(d-> Pair.of(d.atTime(LocalTime.parse(req.getStartTime())),d.atTime(LocalTime.parse(req.getEndTime()))))
                .collect(Collectors.toList());

        var startDates = generatedSessionsDate.stream().map(p->p.getFirst()).collect(Collectors.toList());
        var endDates = generatedSessionsDate.stream().map(p->p.getSecond()).collect(Collectors.toList());
        var existingSession = classSessionService.findAllByTeacherAndStartTimeLikeAndFinishTimeLike(teacher,startDates,endDates);
        var existingClassroomSessions = classSessionService.findAllByClassroomAndStartTimeInAndFinishTimeIn(classroom,startDates,endDates);
        if (existingSession.size()>0){
            throw new ConstraintException(String.format("Can not add teacher to this booked session because teacher with id %s is already booked",req.getTeacherId()));
        }
        if (existingClassroomSessions.size()>0){
            throw new ConstraintException(String.format("Classroom with id %s is booked, can not add sessions",classroomId));
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
}
