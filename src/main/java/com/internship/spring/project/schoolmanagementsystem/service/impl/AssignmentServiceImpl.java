package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AssignmentDTO;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final FileSystemStorageService storageService;
    private final ClassSessionRepository classSessionRepository;
    private final AssignmentResultRepository assignmentResultRepository;
    private final UserRepository userRepository;

    @Override
    public AssignmentDTO createAssignment(Integer sessionId, AssignmentDTO a) {
        var session = classSessionRepository.findById(sessionId).orElseThrow(()-> new ResourceNotFoundException("Session not found"));
        var filename = UUID.randomUUID().toString().concat(".pdf");
        a.setName(a.getFile().getOriginalFilename() );
        a.setFileName(filename);
        storageService.store(a.getFile(),filename);
        var assignmentToSave = AssignmentMapper.toEntity(a);
        assignmentToSave.setClassSession(session);
        return AssignmentMapper.toDto(assignmentRepository.save(assignmentToSave));
    }

    @Override
    public AssignmentDTO findById(Integer id) {
        return assignmentRepository.findById(id)
                .map(AssignmentMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException("Assignment not found"));
    }

    @Override
    public List<AssignmentDTO> findAll() {
        return assignmentRepository.findAll().stream()
                .map(AssignmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAssignment(Integer id) {
     assignmentRepository.findById(id).ifPresentOrElse(a-> {
           a.setDeleted(true);
           assignmentRepository.save(a);
       },()-> new ResourceNotFoundException("Assignment not found") );
    }

    @Override
    public AssignmentResultDTO createAssignmentResult(Integer assignmentId, Integer studentId, AssignmentResultDTO req) {
        var assignment = assignmentRepository.findById(assignmentId).orElseThrow(()-> new ResourceNotFoundException("Assignment not found"));
        var student = userRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
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
               .orElseThrow(()-> new ResourceNotFoundException("Result not found"));
    }

    @Override
    public List<AssignmentResultDTO> findResultByStudentId(Integer studentId) {
        User student = userRepository.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return student.getAssignmentResults()
                .stream().map(AssignmentMapper::toResultDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteResult(Integer id) {
        assignmentRepository.deleteById(id);
    }


}
