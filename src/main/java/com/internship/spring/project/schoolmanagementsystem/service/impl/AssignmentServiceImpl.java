package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.configuration.annotations.IsTeacherAllowed;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentRequest;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentResponse;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentResultDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.AssignmentResult;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.AssignmentMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.AssignmentRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.AssignmentResultRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSessionRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import com.internship.spring.project.schoolmanagementsystem.service.AssignmentService;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    @Value("${base.host}")
    private String baseHost;

    private final AssignmentRepository assignmentRepository;
    private final FileSystemStorageService storageService;
    private final ClassSessionRepository classSessionRepository;
    private final AssignmentResultRepository assignmentResultRepository;
    private final UserRepository userRepository;

    @IsTeacherAllowed
    @Override
    public AssignmentResponse createAssignment(Integer sessionId, AssignmentRequest a) {
        var session = classSessionRepository.findById(sessionId).orElseThrow(()-> new ResourceNotFoundException(format(CLASS_SESSION_NOT_FOUND,sessionId)));
        var fileExtension = ".".concat(a.getFile().getContentType().split("/")[1]);
        var filename = UUID.randomUUID().toString().concat(fileExtension);
        a.setName(a.getName());
        a.setFileName(filename);
        storageService.store(a.getFile(),filename);
        var assignmentToSave = AssignmentMapper.toEntity(a);
        assignmentToSave.setClassSession(session);
        var result = AssignmentMapper.toDto(assignmentRepository.save(assignmentToSave));
        result.setPath(baseHost+result.getFileName());
        return result;
    }

    @Override
    public AssignmentResponse findById(Integer id) {
        return assignmentRepository.findById(id)
                .map(AssignmentMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(format(ASSIGNMENT_NOT_FOUND,id)));
    }

    @Override
    public List<AssignmentResponse> findAll() {
        return assignmentRepository.findAll().stream()
                .map(AssignmentMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteAssignment(Integer id) {
     assignmentRepository.findById(id).ifPresentOrElse(a-> {
           a.setDeleted(true);
           assignmentRepository.save(a);
       },()-> new ResourceNotFoundException(format(ASSIGNMENT_NOT_FOUND,id)));
    }


    @Override
    public AssignmentResultDTO createAssignmentResult(Integer assignmentId, Integer studentId, AssignmentResultDTO req) {
        var assignment = assignmentRepository.findById(assignmentId).orElseThrow(()-> new ResourceNotFoundException(format(ASSIGNMENT_NOT_FOUND,assignmentId)));
        var student = userRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException(format(USER_NOT_FOUND,studentId)));
        AssignmentResult result = new AssignmentResult();
        result.setAssignment(assignment);
        result.setStudent(student);
        result.setTeachersNotes(req.getTeachersNotes());
        result.setGrade(req.getGrade());
        return AssignmentMapper.toResultDTO(assignmentResultRepository.save(result));
    }


    @Override
    public AssignmentResultDTO findResultById(Integer resultId) {
       return assignmentResultRepository.findById(resultId)
               .map(AssignmentMapper::toResultDTO)
               .orElseThrow(()-> new ResourceNotFoundException(format(RESULT_NOT_FOUND,resultId)));
    }

    @Override
    public List<AssignmentResultDTO> findResultByStudentId(Integer studentId) {
        User student = userRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException(format(USER_NOT_FOUND,studentId)));
        return student.getAssignmentResults()
                .stream().map(AssignmentMapper::toResultDTO)
                .collect(Collectors.toList());
    }

    @IsTeacherAllowed
    @Override
    public void deleteResult(Integer id) {
        assignmentRepository.findById(id).ifPresentOrElse(a->{
                    a.setDeleted(true);
                    assignmentRepository.save(a);},
                ()-> new ResourceNotFoundException(format(ASSIGNMENT_NOT_FOUND,id)));

    }


}
