package com.internship.spring.project.schoolmanagementsystem.controller;


import com.internship.spring.project.schoolmanagementsystem.BaseTest;
import com.internship.spring.project.schoolmanagementsystem.SchoolManagementSystemApplication;
import com.internship.spring.project.schoolmanagementsystem.domain.auth.LoginRequest;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.*;

@SpringBootTest(classes = SchoolManagementSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends BaseTest {

    @BeforeEach
    public void init(){
        var req = new LoginRequest("school.admin@mail.com","password");
        loginResponse = doLogin(req);
    }

    @Test
    public void test_get_users_by_id(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",loginResponse.getToken());
        HttpEntity<UserDTO> entity = new HttpEntity<>(null, headers);

        ResponseEntity<UserDTO> response = restTemplate.exchange(
                createURLWithPort("/api/users/19"),
                HttpMethod.GET, entity, UserDTO.class);

        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Assertions.assertEquals("Susie",response.getBody().getFirstName());
        Assertions.assertEquals("Jacobi",response.getBody().getLastName());
    }



}
