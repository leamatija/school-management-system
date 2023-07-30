package com.internship.spring.project.schoolmanagementsystem.configuration.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@classSessionRepository.findFirstByIdAndTeacher_Id(#sessionId,authentication.name)!=null")
public @interface IsTeacherAllowed {
}
