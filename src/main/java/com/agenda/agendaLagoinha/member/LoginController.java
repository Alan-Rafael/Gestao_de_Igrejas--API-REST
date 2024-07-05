package com.agenda.agendaLagoinha.member;


import com.agenda.agendaLagoinha.member.exception.AuthenticationFailedException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("loginMembers")
public class LoginController {

    final MemberLoginService service;

    public LoginController(MemberLoginService service) {
        this.service = service;
    }



    @PostMapping("entrar")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String,?> login(@RequestBody @Valid MemberAuthDto memberAuthDto) {
        try {
            String token = this.service.realizarLogin(memberAuthDto);
            Map<String, String> map = new HashMap<String, String>();
            map.put("token", token);

            return map;
        } catch (AuthenticationFailedException e) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("error", e.getMessage());

            return map;
        }
    }
}
