package com.agenda.agendaLagoinha.member;


import com.agenda.agendaLagoinha.requests.MemberRequests.MemberAuthDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String findByUsername(MemberAuthDto memberAuthDto) {
        var pessoa = memberRepository.findByEmail(memberAuthDto.getEmail());
        if(pessoa!=null){

            var passwordMatchers = this.passwordEncoder.matches(memberAuthDto.getPassword(), pessoa.getPassword());

            if(!passwordMatchers){
                return null;
            }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("javavagas")
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(30)))
                .withSubject(pessoa.getId().toString())
                .sign(algorithm);
        System.out.println(token);
        return token;
        }
        return null;
    }

    public Member findMembro(String email){
        return this.memberRepository.findByEmail(email);
    }


}
