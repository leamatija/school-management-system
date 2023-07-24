package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSubjectDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSubject;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSubjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClassSubjectServiceTest {

    @Autowired
    ClassSubjectService toTest;
    @MockBean
    ClassSubjectRepository classSubjectRepository;

    @Test
    public void test_createSubject_ok(){
        Mockito.doReturn(new ClassSubject()).when(classSubjectRepository).save(Mockito.any());
        ClassSubjectDTO out = toTest.createSubject(new ClassSubjectDTO());
        assertNotNull(out);
    }

    @Test
    public void test_findById_ok(){
        Mockito.doReturn(Optional.of(new ClassSubject())).when(classSubjectRepository).findById(Mockito.anyInt());
        ClassSubjectDTO out = toTest.findById(1);
        assertNotNull(out);
    }

    @Test
    public void test_findById_ko(){
        Mockito.doThrow(new ResourceNotFoundException("Subject not found"))
                .when(classSubjectRepository).findById(Mockito.anyInt());
        Throwable throwable=assertThrows(Throwable.class,()-> toTest.findById(1));
        assertEquals(ResourceNotFoundException.class,throwable.getClass());
    }

    @Test
    public void test_findALl_ok(){
        List<ClassSubject> subjects = new ArrayList<>();
        Mockito.doReturn(subjects).when(classSubjectRepository).findAll();
        List<ClassSubjectDTO> out =toTest.findAll();
        assertNotNull(out);
    }

    @Test
    public void test_deleteSubject_ok(){
        Mockito.doNothing().when(classSubjectRepository).deleteById(1);
        toTest.deleteSubject(1);
    }

}
