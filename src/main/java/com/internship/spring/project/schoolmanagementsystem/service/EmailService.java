package com.internship.spring.project.schoolmanagementsystem.service;

import java.util.List;

public interface EmailService {
    void sendSimpleMailMessage(String to, String message);
    void sendMimeMessageWithAttachments(String to,String text, List<String> filenames);
}
