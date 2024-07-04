package com.agenda.agendaLagoinha.member;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
public class LoginController {

    final MemberLoginService service;

    public LoginController(MemberLoginService service) {
        this.service = service;
    }
    @PostMapping
    public String login(@RequestBody  MemberAuthDto memberAuthDto) throws AuthenticationException {
        return this.service.realizarLogin(memberAuthDto);
    }
}
