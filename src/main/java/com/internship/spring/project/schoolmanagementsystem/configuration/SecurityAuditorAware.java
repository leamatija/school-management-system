package com.internship.spring.project.schoolmanagementsystem.configuration;
import com.internship.spring.project.schoolmanagementsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Optional;


@Slf4j
public class SecurityAuditorAware implements AuditorAware<Integer> {

    private UserRepository userRepository;
    public SecurityAuditorAware(UserRepository userRepository) {
    }

    @Override
    public Optional<Integer> getCurrentAuditor() {

        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       principal.getClaims();
        log.info("Sub = {}",principal.getClaims());
        var sub = Integer.parseInt(principal.getClaim("sub"));
        return Optional.of(sub);
    }

}
