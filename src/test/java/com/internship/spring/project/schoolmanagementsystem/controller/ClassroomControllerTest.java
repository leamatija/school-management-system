package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.BaseTest;
import com.internship.spring.project.schoolmanagementsystem.SchoolManagementSystemApplication;
import com.internship.spring.project.schoolmanagementsystem.domain.auth.LoginRequest;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.classroom.ClassroomDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(classes = SchoolManagementSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassroomControllerTest extends BaseTest {

    @BeforeEach
    public void init(){
        var req = new LoginRequest("school.admin@mail.com","password");
        loginResponse = doLogin(req);
    }

    @Test
    public void test_get_classroom_by_id(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",loginResponse.getToken());
        HttpEntity<ClassroomDTO> entity = new HttpEntity<>(null, headers);

        ResponseEntity<ClassroomDTO> response = restTemplate.exchange(
                createURLWithPort("/api/classroom/3"),
                HttpMethod.GET, entity, ClassroomDTO.class);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals("Grade 1",response.getBody().getName());
    }

    @Test
    public void test_list_classrooms(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",loginResponse.getToken());
        HttpEntity<List<ClassroomDTO>> entities = new HttpEntity<>(null, headers);

        ResponseEntity<List<ClassroomDTO>> response = restTemplate.exchange(
                createURLWithPort("/api/classroom/list"),
                HttpMethod.GET, entities, new ParameterizedTypeReference<List<ClassroomDTO>>(){});

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertTrue(response.getBody().size()>0);
    }

    @Test
    public void test_create_classroom(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",loginResponse.getToken());
        var classRoom = new ClassroomDTO();
        classRoom.setName("Grade 10");
        classRoom.setCapacity(15);
        classRoom.setActive(true);
        classRoom.setStartDate("2023-09-05");
        classRoom.setEndDate("2023-05-15");
        HttpEntity<ClassroomDTO> entity = new HttpEntity<>(classRoom, headers);
        ResponseEntity<ClassroomDTO> response = restTemplate.exchange(
                createURLWithPort("/api/classroom"),
                HttpMethod.POST, entity, ClassroomDTO.class);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
