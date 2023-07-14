package com.internship.spring.project.schoolmanagementsystem.configuration;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
public class SecurityAuditorAware implements AuditorAware<Integer> {

    private UserRepository userRepository;
    public SecurityAuditorAware(UserRepository userRepository) {
    }


    @Override
    public Optional<Integer> getCurrentAuditor() {

        Integer sub = null;
        if (SecurityContextHolder.getContext().getAuthentication()!=null){
            Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            sub = Integer.parseInt(principal.getClaim("sub"));
        }
        return sub==null? Optional.empty():Optional.of(sub);
    }

}
