package com.internship.spring.project.schoolmanagementsystem;

import com.internship.spring.project.schoolmanagementsystem.domain.auth.LoginRequest;
import com.internship.spring.project.schoolmanagementsystem.domain.auth.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class BaseTest {

    @LocalServerPort
    protected int port;
    protected TestRestTemplate restTemplate = new TestRestTemplate();
    protected LoginResponse loginResponse;

    protected LoginResponse doLogin(LoginRequest login ){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<LoginRequest> entity = new HttpEntity<>(login, headers);
        ResponseEntity<LoginResponse> response = restTemplate.exchange(
                createURLWithPort("/api/auth/login"),
                HttpMethod.POST, entity, LoginResponse.class);
        return response.getBody();
    }

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
