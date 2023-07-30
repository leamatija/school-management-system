package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${fs.root-dir}")
    public String attachmentRootDir;
    public static final String UTF_8_ENCODING = "UTF-8";
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendSimpleMailMessage( String to,String subject, String message) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setSubject(subject);
            email.setFrom(fromEmail);
            email.setTo(to);
            email.setText(message);
            emailSender.send(email);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }

    }

    @Override
    public void sendMimeMessageWithAttachments(String to,String text, List<String> filenames) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,UTF_8_ENCODING);
            helper.setSubject("Student's reports");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text);
            var attachmentList = getAttachment(filenames);
            attachmentList.stream().forEach(a-> {
                try {
                    helper.addAttachment(a.getFilename(),a);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
            emailSender.send(message);

        }catch (Exception e){
        }
    }

    private List<FileSystemResource> getAttachment(List<String> filenames){
        return filenames.stream()
                .map(f-> new FileSystemResource(new File(attachmentRootDir.concat(f))))
                .toList();
    }

}
