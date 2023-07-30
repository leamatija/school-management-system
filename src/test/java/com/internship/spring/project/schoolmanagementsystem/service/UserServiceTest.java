package com.internship.spring.project.schoolmanagementsystem.service;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.exception.ResourceNotFoundException;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService toTest;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    @Test
    public void test_createUser_ok(){
        Mockito.doReturn("anyPass").when(encoder).encode(Mockito.anyString());
        Mockito.doReturn(new User()).when(userRepository).save(Mockito.any());
        UserDTO out = toTest.createUser(new UserDTO(),"STUDENT");
        assertNotNull(out);
    }

    @Test
    public void test_updateUser_ok(){
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        User fakeUser = new User();
        Mockito.doReturn(fakeUser).when(userRepository).save(Mockito.any());
        UserDTO fakeDto = new UserDTO();
        UserDTO out = toTest.updateUser(fakeDto,1);
        assertNotNull(out);
    }

    @Test
    public void test_findUserById_ok(){
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        UserDTO out = toTest.findUserById(1);
        assertNotNull(out);
    }
    @Test
    public void test_findUserById_ko(){
        Mockito.doThrow(new ResourceNotFoundException("User not found"))
                .when(userRepository).findById(Mockito.anyInt());
        Throwable throwable=assertThrows(Throwable.class,()-> toTest.findUserById(1));
        assertEquals(ResourceNotFoundException.class,throwable.getClass());
    }

    @Test
    public void test_deleteUser_ok(){
        Mockito.doNothing().when(userRepository).deleteById(Mockito.anyInt());
        toTest.deleteUser(1);
    }




}
