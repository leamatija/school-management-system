package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassroomDTO;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.domain.mapper.ClassroomMapper;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSessionRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassroomRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import com.internship.spring.project.schoolmanagementsystem.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;
    private final ClassSessionRepository classSessionRepository;

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

    @Override
    public void addClassSessionsToClass(Integer classroomId, List<Integer> classSessionsId, Integer teacherId) {
        Classroom c = classroomRepository.findById(classroomId).orElseThrow(()-> new ResourceNotFoundException(String.format("Classroom with id %s not found",classroomId)));

        User teacher = userRepository.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException(String.format("User with id %s not found",teacherId)));

    }
}
