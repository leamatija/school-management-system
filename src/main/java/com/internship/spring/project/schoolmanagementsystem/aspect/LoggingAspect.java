package com.internship.spring.project.schoolmanagementsystem.aspect;

import lombok.RequiredArgsConstructor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Aspect
@Component
public class LoggingAspect {

    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final HttpServletRequest httpServletRequest;

    @Before("execution(* com.internship.spring.project.schoolmanagementsystem.controller.*.*(..))")
    public void logHttpRequestResponse(JoinPoint joinPoint){
        StringBuilder reqMessage = new StringBuilder();
        reqMessage.append("[REQUEST] ").append("method = [").append(httpServletRequest.getMethod()).append("]");
        reqMessage.append(" path = [").append(httpServletRequest.getRequestURI()).append("] ");
        reqMessage.append("location = [").append(joinPoint.getSignature()).append("] ");
        logger.info("{}", reqMessage);
    }




}
