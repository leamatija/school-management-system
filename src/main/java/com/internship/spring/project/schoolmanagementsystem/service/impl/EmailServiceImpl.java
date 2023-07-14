package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendSimpleMailMessage(String name, String to, String message) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setSubject("Student report");
            msg.setFrom("leamatija22@gmail.com");
            msg.setTo(to);
            msg.setText(message);
            emailSender.send(msg);
        } catch (Exception exception) {
            System.out.println("Mail sent...");
            throw new RuntimeException(exception.getMessage());
        }

    }

}
