package com.internship.spring.project.schoolmanagementsystem.controller;

import com.internship.spring.project.schoolmanagementsystem.configuration.TokenService;
import com.internship.spring.project.schoolmanagementsystem.domain.auth.LoginRequest;
import com.internship.spring.project.schoolmanagementsystem.domain.auth.LoginResponse;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.ChangePasswordDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.ForgotPasswordDTO;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.UserDTO;
import com.internship.spring.project.schoolmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor @Validated
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        try {
            return ResponseEntity.ok(tokenService.getToken(request));
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestParam String role, @RequestBody @Valid UserDTO u){
        return ResponseEntity.ok(userService.createUser(u,role));
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> getTokenByRefreshToken(@RequestParam String refreshToken){
        return ResponseEntity.ok(tokenService.generateTokenFromRefreshToken(refreshToken));
    }

    @PostMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO req){
        userService.updatePassword(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/forgot")
    public ResponseEntity<Void> forgotPassword(@RequestBody ForgotPasswordDTO req){
        userService.forgotPassword(req.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
