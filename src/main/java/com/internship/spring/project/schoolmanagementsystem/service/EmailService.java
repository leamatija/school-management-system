package com.internship.spring.project.schoolmanagementsystem.service;

public interface EmailService {
    void sendSimpleMailMessage(String name, String to, String message);
//    void sendMimeMessageWithAttachments(String name, String to,String message);
}
