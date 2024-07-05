package com.agenda.agendaLagoinha.member;


import com.agenda.agendaLagoinha.member.exception.AuthenticationFailedException;
import com.agenda.agendaLagoinha.member.exception.MemberNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController()
@RequestMapping("loginMembers")
public class LoginController {

    final MemberLoginService service;

    public LoginController(MemberLoginService service) {
        this.service = service;
    }

    @PostMapping("entrar")
    public ResponseEntity<String> login(@RequestBody @Valid MemberAuthDto memberAuthDto) {
        try {
            return this.service.realizarLogin(memberAuthDto);
        } catch (AuthenticationFailedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }
}
