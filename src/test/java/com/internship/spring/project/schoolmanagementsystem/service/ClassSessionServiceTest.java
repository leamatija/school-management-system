package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.ClassSessionDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSubject;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSessionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClassSessionServiceTest {

    @Autowired
    private ClassSessionService toTest;
    @MockBean
    private ClassSessionRepository classSessionRepository;

    @Test
    public void test_addTopic_ok(){
        Mockito.doReturn(Optional.of(new ClassSession())).when(classSessionRepository).findById(Mockito.anyInt());
        ClassSession fake = new ClassSession();
        fake.setTopic("topic");
        Mockito.doReturn(fake).when(classSessionRepository).save(fake);
    }

    @Test
    public void test_setCancellation_ok(){
        Mockito.doReturn(Optional.of(new ClassSession())).when(classSessionRepository).findById(Mockito.anyInt());
        ClassSession fake = new ClassSession();
        fake.setCancelled(true);
        Mockito.doReturn(fake).when(classSessionRepository).save(fake);
    }

    @Test
    public void test_deleteClassSession_ok(){
        Mockito.doNothing().when(classSessionRepository).deleteById(Mockito.anyInt());
        toTest.deleteClassSession(1);
    }

    @Test
    public void test_findSessionByClassroom_ok(){
        ClassSession fake = new ClassSession();
        fake.setStartTime(LocalDateTime.now());
        fake.setFinishTime(LocalDateTime.now().plusHours(1));
        ClassSubject fakeSub = new ClassSubject();
        fake.setClassSubject(fakeSub);
        Mockito.doReturn(List.of(fake)).when(classSessionRepository).findByClassroomId(Mockito.anyInt());
        List<ClassSessionDTO> out = toTest.getClassSessionsByClassroom(1);
        assertNotNull(out);
    }


}
