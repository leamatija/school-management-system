package com.internship.spring.project.schoolmanagementsystem.service.impl;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.ClassSessionMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSessionRepository;
import com.internship.spring.project.schoolmanagementsystem.service.ClassSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassSessionServiceImpl implements ClassSessionService {

    private final ClassSessionRepository classSessionRepository;

    @Override
    public ClassSessionDTO createClassSession(ClassSessionDTO classSessionDTO) {
        return ClassSessionMapper.toDto(classSessionRepository.save(ClassSessionMapper.toEntity(classSessionDTO)));
    }

    @Override
    public ClassSessionDTO addTeacherToClassSession(Integer id, UserDTO userDTO) {
        return null;
    }

    @Override
    public ClassSessionDTO updateClassSession(Integer id, ClassSessionDTO classSessionDTO) {
        return classSessionRepository.findById(id)
                .map(classSession-> ClassSessionMapper.toEntity(classSession,classSessionDTO))
                .map(classSessionRepository::save)
                .map(ClassSessionMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(String.format("Classroom with id %s not found",id)));
    }

    @Override
    public Void deleteClassSession(Integer id) {
        classSessionRepository.deleteById(id);
        return null;
    }

    @Override
    public List<ClassSessionDTO> getClassSessionsByClassroom(Integer classroomId) {
        return classSessionRepository.findByClassroomId(classroomId)
                .stream()
                .map(ClassSessionMapper::toDto)
                .collect(Collectors.toList());
    }


















}
