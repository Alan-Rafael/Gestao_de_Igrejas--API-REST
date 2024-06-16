package com.agenda.agendaLagoinha.Admin;


import com.agenda.agendaLagoinha.member.exception.MemberNotFoundException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AdminAuthService {
    @Value("java@123#")
    private String secretKey;

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public  String autorizar(AdminAuthDto adminAuthDto)throws AuthenticationException{
        var admin = this.adminRepository.findByEmail(adminAuthDto.getEmail()).orElseThrow(
                MemberNotFoundException::new
        );
        var passwordMatches = this.passwordEncoder.matches(adminAuthDto.getPassword(), admin.getPassword());
        if(!passwordMatches){
            throw new AuthenticationException();
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javavagas")
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(30)))
                .withSubject(admin.getId().toString())
                .sign(algorithm);
        return token;
    }













}
