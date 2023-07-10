package com.internship.spring.project.schoolmanagementsystem.service.impl;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.TopicRequestDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.ClassSessionMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSessionRepository;
import com.internship.spring.project.schoolmanagementsystem.service.ClassSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.CLASSROOM_NOT_FOUND;
import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.CLASS_SESSION_NOT_FOUND;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClassSessionServiceImpl implements ClassSessionService {

    private final ClassSessionRepository classSessionRepository;

    @Override
    public void addTopic(Integer sessionId, TopicRequestDTO topicReq) {
        var session = classSessionRepository.findById(sessionId).orElseThrow(
                ()-> new ResourceNotFoundException(format(CLASS_SESSION_NOT_FOUND,sessionId)));
        session.setTopic(topicReq.getTopic());
        classSessionRepository.save(session);
    }

    @Override
    public void setCancellation(Integer sessionId, Boolean cancelled) {
        var session = classSessionRepository.findById(sessionId).orElseThrow(
                ()-> new ResourceNotFoundException(format(CLASS_SESSION_NOT_FOUND,sessionId)));
        session.setCancelled(cancelled);
        classSessionRepository.save(session);
    }

    @Override
    public void deleteClassSession(Integer id) {
        classSessionRepository.findById(id).ifPresentOrElse(c->{
            c.setDeleted(true);
            classSessionRepository.save(c);
        },()->new ResourceNotFoundException(format(CLASSROOM_NOT_FOUND,id)));
    }

    @Override
    public List<ClassSessionDTO> getClassSessionsByClassroom(Integer classroomId) {
        return classSessionRepository.findByClassroomId(classroomId)
                .stream()
                .map(ClassSessionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ClassSession> findAllByTeacherAndStartTimeInAndFinishTimeIn(User teacher, List<LocalDateTime> startLocalDateTimes, List<LocalDateTime> finishLocalDateTime){
        return classSessionRepository.findAllByTeacherAndStartTimeInAndFinishTimeIn(teacher,startLocalDateTimes,finishLocalDateTime);
    }

    @Override
    public List<ClassSession> findAllByClassroomAndStartTimeInAndFinishTimeIn(Classroom classroom, List<LocalDateTime> startLocalDateTimes, List<LocalDateTime> finishLcalDateTime) {
        return classSessionRepository.findAllByClassroomAndStartTimeInAndFinishTimeIn(classroom,startLocalDateTimes,finishLcalDateTime);
    }


}
