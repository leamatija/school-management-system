package com.internship.spring.project.schoolmanagementsystem;

import com.github.javafaker.Faker;
import com.internship.spring.project.schoolmanagementsystem.configuration.SecurityAuditorAware;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.UserRole;
import com.internship.spring.project.schoolmanagementsystem.repository.ClassroomRepository;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import com.internship.spring.project.schoolmanagementsystem.service.EmailService;
import com.internship.spring.project.schoolmanagementsystem.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
public class SchoolManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailService emailService;

	@Autowired
	ReportsService reportsService;

	@Autowired
	ClassroomRepository classroomRepository;


	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//CREATE FAKE USERS

//		List<User> teachers = IntStream.rangeClosed(1,10)
//				.mapToObj(p->buildUser(UserRole.TEACHER)).toList();
//		List<User> students = IntStream.rangeClosed(1,100)
//				.mapToObj(p->buildUser(UserRole.STUDENT)).toList();
//		userRepository.saveAll(teachers);
//		userRepository.saveAll(students);

		//GENERATE REPORTS
//		var reports = userRepository.getStudentReports(12,LocalDateTime.parse("2023-09-11T00:00:00"),LocalDateTime.parse("2023-09-19T00:00:00"));
//		reportsService.getStudentReports(LocalDateTime.parse("2023-09-11T00:00:00"),LocalDateTime.parse("2023-09-19T00:00:00"));
	}

















//	public User buildUser (UserRole role){
//		Faker faker = new Faker();
//		User u = new User();
//		u.setFirstName(faker.name().firstName());
//		u.setLastName(faker.name().lastName());
//		u.setPhoneNumber(faker.phoneNumber().cellPhone());
//		var email = u.getFirstName()+"."+u.getLastName()+"@email.com";
//		u.setEmail(email);
//		u.setPassword(passwordEncoder.encode("password"));
//		u.setParentContact(faker.internet().emailAddress());
//		u.setRole(role);
//		return u;
//	}


}
