package com.internship.spring.project.schoolmanagementsystem.configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


public class SecurityAuditorAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Integer sub = TokenService.getLoggedUser();
        return sub==null? Optional.empty():Optional.of(sub);
    }

}
