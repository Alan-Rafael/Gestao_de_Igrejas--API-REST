package com.agenda.agendaLagoinha.member;

import com.agenda.agendaLagoinha.member.exception.AuthenticationFailedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loginMember")
public class LoginController {

    final MemberLoginService service;

    public LoginController(MemberLoginService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String loginPage(){

        return "loginPage";
    }

    @PostMapping("/loginPage.html")
    public void login(@RequestParam String email, @RequestParam String password) {
       MemberAuthDto memberAuthDto = new MemberAuthDto(email, password);
        try {
            this.service.realizarLogin(memberAuthDto);
        } catch (AuthenticationFailedException e) {
             e.getMessage();
        }
    }
}
