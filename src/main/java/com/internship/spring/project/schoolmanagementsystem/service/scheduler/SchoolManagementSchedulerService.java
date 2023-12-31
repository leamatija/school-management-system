package com.internship.spring.project.schoolmanagementsystem.service.scheduler;

import com.internship.spring.project.schoolmanagementsystem.domain.entity.Classroom;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassroomRepository;
import com.internship.spring.project.schoolmanagementsystem.service.ReportsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchoolManagementSchedulerService {

    private final ClassroomRepository classroomRepository;
    private final ReportsService reportsService;

//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 0 0 1 9 ?")
    public void classroomDeactivation() {
        LocalDate currentDate = LocalDate.now();
        List<Classroom> classrooms = classroomRepository.findAllByStartDateBetween(currentDate.minusYears(1),currentDate)
                .stream()
//                .peek(c-> System.err.println("deactivating " + c.getName()))
                .map(c-> {
                    c.setActive(false);
                    return c;
                }).collect(Collectors.toList());
        classroomRepository.saveAll(classrooms);
    }



   @Scheduled(cron = "0 0 12 1 * ?")
    public void generateMonthlyReports() throws IOException {
        LocalDateTime currentDate = LocalDateTime.now();
        reportsService.getStudentReports(currentDate.minusMonths(1),currentDate);
         System.err.println("reports sent");
    }
    













}
