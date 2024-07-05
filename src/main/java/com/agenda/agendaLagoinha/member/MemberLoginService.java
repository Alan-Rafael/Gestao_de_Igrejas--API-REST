package com.agenda.agendaLagoinha.member;


import com.agenda.agendaLagoinha.member.exception.AuthenticationFailedException;
import com.agenda.agendaLagoinha.member.exception.MemberNotFoundException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class MemberLoginService {

    @Value("java@123#")
    private String secretKey;
    final MemberRepository memberRepository;
    final PasswordEncoder passwordEncoder;

    public MemberLoginService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> realizarLogin(MemberAuthDto memberAuthDto) {
        Member pessoa = this.memberRepository.findByEmail(memberAuthDto.getEmail()).orElseThrow(
                MemberNotFoundException::new
        );

        var passwordMatches = this.passwordEncoder.matches(memberAuthDto.getPassword(), pessoa.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationFailedException("Senha incorreta.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Seja bem-vindo, " + pessoa.getName());
    }
}
