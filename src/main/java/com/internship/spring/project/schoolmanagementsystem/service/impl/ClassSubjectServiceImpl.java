package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSubjectDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.ClassSubjectMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSubjectRepository;
import com.internship.spring.project.schoolmanagementsystem.service.ClassSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.ATTENDANCE_NOT_FOUND;
import static com.internship.spring.project.schoolmanagementsystem.domain.exception.ExceptionConstants.CLASS_SUBJECT_NOT_FOUND;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ClassSubjectServiceImpl implements ClassSubjectService {

    private final ClassSubjectRepository subjectRepository;

    @Override
    public ClassSubjectDTO createSubject(ClassSubjectDTO classSubjectDTO) {
        return ClassSubjectMapper.toDto(subjectRepository
                .save(ClassSubjectMapper.toEntity(classSubjectDTO)));
    }

    @Override
    public ClassSubjectDTO findById(Integer id) {
        return subjectRepository.findById(id)
                .map(ClassSubjectMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(format(CLASS_SUBJECT_NOT_FOUND,id)));
    }

    @Override
    public List<ClassSubjectDTO> findAll() {
        return subjectRepository.findAll().stream()
                .map(ClassSubjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubject(Integer id) {
        subjectRepository.findById(id).ifPresentOrElse(a->{
                    a.setDeleted(true);
                    subjectRepository.save(a);},
                ()-> new ResourceNotFoundException(format(CLASS_SUBJECT_NOT_FOUND,id)));

    }
}
