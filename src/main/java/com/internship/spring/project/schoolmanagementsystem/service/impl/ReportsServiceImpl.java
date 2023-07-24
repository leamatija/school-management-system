package com.internship.spring.project.schoolmanagementsystem.service.impl;

import com.internship.spring.project.schoolmanagementsystem.domain.dto.AbsenceReports;
import com.internship.spring.project.schoolmanagementsystem.domain.dto.StudentReport;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassroomRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import com.internship.spring.project.schoolmanagementsystem.service.EmailService;
import com.internship.spring.project.schoolmanagementsystem.service.ReportsService;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportsServiceImpl implements ReportsService {

    private String MONTHLY = "Student's monthly reports";

    @Value("${fs.root-dir}")
    private String tempRoot;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ClassroomRepository classroomRepository;


    @Transactional
    @Override
    public void getStudentReports(LocalDateTime from, LocalDateTime to) throws IOException {

        var classrooms = classroomRepository.findAll();

        classrooms.forEach(c ->{
            c.getStudents().stream().filter(s->s.getParentContact().equals("leamatia@hotmail.com"))
                    .forEach(s ->{
                var reports = userRepository.getStudentReports(s.getId(),from,to);
                var fileName = "temp/"+UUID.randomUUID().toString().concat(".csv");
                var tmpFileLocation = tempRoot+fileName;
                var tmpFile = new File(tmpFileLocation);
                String csvHeaders[] = {"Student","Classroom","Subject","Topic","Session_date","Presence","Participation","Teachers_notes","Assignment_name","Assignment_grade","Assignment_notes"};
                FileWriter outputFile = null;
                try {
                    outputFile = new FileWriter(tmpFile);

                   CSVWriter writer = new CSVWriter(outputFile);
                   writer.writeNext(csvHeaders);

                   reports.forEach(r -> {
                       String[] toWrite = {r.getStudent(),r.getClassroom(),r.getSubject_name(),r.getTopic(),r.getSession_date().toString()
                               ,r.getPresence().toString(),r.getParticipation()!=null?r.getParticipation().toString():null,r.getTeachers_notes(),r.getAssignment_name(),
                               r.getAssignment_grade()!=null?r.getAssignment_grade().toString():null,r.getAssignment_notes()};
                       writer.writeNext(toWrite);
                });
                      writer.close();
                      emailService.sendMimeMessageWithAttachments(s.getParentContact(),MONTHLY,Arrays.asList(fileName));
                   Files.delete(Path.of(tmpFileLocation));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    @Override
    public List<AbsenceReports> getAbsencesForEveryStudent(){
        return userRepository.getAbsencesForEveryStudent();
    }


}
