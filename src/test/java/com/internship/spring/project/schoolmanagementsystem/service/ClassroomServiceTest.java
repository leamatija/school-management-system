package com.internship.spring.project.schoolmanagementsystem.service;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomSessionRequestDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSession;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.ClassSubject;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassSubjectRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassroomRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClassroomServiceTest {

    @Autowired
    private ClassroomService toTest;
    @MockBean
    private ClassroomRepository classroomRepository;
    @MockBean
    private ClassSubjectRepository classSubjectRepository;
    @MockBean
    private ClassSessionService classSessionService;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void test_createClassroom_ok(){
        Mockito.doReturn(new Classroom()).when(classroomRepository).save(Mockito.any());
        ClassroomDTO out = toTest.createClassroom(new ClassroomDTO());
        assertNotNull(out);
    }

    @Test
    public void  test_update_classroom_ok(){
        Mockito.doReturn(Optional.of(new Classroom())).when(classroomRepository).findById(Mockito.anyInt());
        Classroom fakeClassroom = new Classroom();
        Mockito.doReturn(fakeClassroom).when(classroomRepository).save(Mockito.any());
        ClassroomDTO fakeDto = new ClassroomDTO();
        ClassroomDTO out = toTest.updateClassroom(2,fakeDto);
        assertNotNull(out);
    }

    @Test
    public void test_deleteClassroom_ok(){
        Mockito.doNothing().when(classroomRepository).deleteById(1);
        toTest.deleteClassroom(1);
    }

    @Test
    public void test_findALlClassrooms_ok(){
        List<Classroom> classrooms = new ArrayList<>();
        Mockito.doReturn(classrooms).when(classroomRepository).findAll();
        List<ClassroomDTO> out =toTest.getAllClassrooms();
        assertNotNull(out);
    }

    @Test
    public void test_getClassroomById_ok(){
        Classroom fakeC = new Classroom();
        fakeC.setStartDate(LocalDate.now());
        fakeC.setEndDate(LocalDate.now().plusDays(1));
        Mockito.doReturn(Optional.of(fakeC)).when(classroomRepository).findById(Mockito.any());
        ClassroomDTO fake = new ClassroomDTO();
        fake.setStartDate(LocalDate.now().toString());
        assertNotNull(toTest.getClassroomById(fake.getId()));
    }

    @Test
    public void test_getClassroomById_ko(){
        Mockito.doThrow(new ResourceNotFoundException("Classroom not found"))
                .when(classroomRepository).findById(Mockito.anyInt());
        Throwable throwable=assertThrows(Throwable.class,()-> toTest.getClassroomById(4));
        assertEquals(ResourceNotFoundException.class,throwable.getClass());
    }

    @Test
    public void test_addSessionsToClass_ok(){
        Mockito.doReturn(Optional.of(new Classroom())).when(classroomRepository).findById(Mockito.anyInt());
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        Mockito.doReturn(Optional.of(new ClassSubject())).when(classSubjectRepository).findById(Mockito.anyInt());
        List<ClassSession> fakeSessions = new ArrayList<>();
        Mockito.doReturn(fakeSessions).when(classSessionService).findAllByTeacherAndStartTimeInAndFinishTimeIn(Mockito.any(),Mockito.any(),Mockito.any());
        Mockito.doReturn(fakeSessions).when(classSessionService).findAllByClassroomAndStartTimeInAndFinishTimeIn(Mockito.any(),Mockito.any(),Mockito.any());
        Mockito.doReturn(new Classroom()).when(classroomRepository).save(Mockito.any());
        ClassroomSessionRequestDTO out = new ClassroomSessionRequestDTO();
        out.setStartTime("12:30");
        out.setEndTime("13:00");
        out.setStartLocalDate(LocalDate.now().toString());
        out.setEndLocalDate(LocalDate.now().plusDays(1).toString());
        out.setSubjectId(1);
        out.setTeacherId(1);
        out.setWeekDays(Arrays.asList(1,2,3));
        toTest.addClassSessionsToClass(1,out);

    }



}
