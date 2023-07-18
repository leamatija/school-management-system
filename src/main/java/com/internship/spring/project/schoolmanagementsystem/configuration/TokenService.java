package com.internship.spring.project.schoolmanagementsystem.configuration;

import com.internship.spring.project.schoolmanagementsystem.domain.auth.LoginRequest;
import com.internship.spring.project.schoolmanagementsystem.domain.auth.LoginResponse;
import com.internship.spring.project.schoolmanagementsystem.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final JwtEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtDecoder jwtDecoder;

    public LoginResponse getToken(LoginRequest req) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(),req.getPassword()));
        var token ="Bearer ".concat(generateToken(authentication,Instant.now().plus(1, ChronoUnit.HOURS)));
        var refreshToken = generateToken(authentication,Instant.now().plus(10, ChronoUnit.DAYS));
        return new LoginResponse(token,refreshToken);
    }

    public LoginResponse generateTokenFromRefreshToken(String refreshToken){
        var refresh = jwtDecoder.decode(refreshToken);
        JwtClaimsSet tokenClaims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .subject(refresh.getSubject())
                .claims(c->c.putAll(refresh.getClaims()))
                .build();
        JwtClaimsSet refreshTokenClaims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(10, ChronoUnit.DAYS))
                .subject(refresh.getSubject())
                .claims(c->c.putAll(refresh.getClaims()))
                .build();
        var token ="Bearer ".concat(this.encoder.encode(JwtEncoderParameters.from(tokenClaims)).getTokenValue());
        var refreshT =this.encoder.encode(JwtEncoderParameters.from(refreshTokenClaims)).getTokenValue();
        return new LoginResponse(token,refreshT);

    }

    public String generateToken (Authentication authentication, Instant expireAt){
        User user = (User) authentication.getPrincipal();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet tokenClaims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(expireAt)
                .subject(user.getId().toString())
                .claim("roles", scope)
                .build();
        var token =this.encoder.encode(JwtEncoderParameters.from(tokenClaims)).getTokenValue();
        return token;


    }

    public static Integer getLoggedUser(){
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Integer.parseInt(principal.getClaim("sub"));
    }
}

