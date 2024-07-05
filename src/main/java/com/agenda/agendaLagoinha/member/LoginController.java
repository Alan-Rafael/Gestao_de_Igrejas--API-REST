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

    @PostMapping("/entrar")
    public String login(@RequestParam String email, @RequestParam String password) {
       MemberAuthDto memberAuthDto = new MemberAuthDto(email, password);
        try {
            this.service.realizarLogin(memberAuthDto);
            return "redirect:/main.html";
        } catch (AuthenticationFailedException e) {
            return e.getMessage();
        }
    }
}
