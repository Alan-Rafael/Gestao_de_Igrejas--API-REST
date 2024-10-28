package com.agenda.agendaLagoinha.member;

import com.agenda.agendaLagoinha.requests.MemberRequests.MemberAuthDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/loginMember")
public class LoginController {

    final MemberLoginService loginservice;
    final MemberService memberService;

    public LoginController(MemberLoginService loginservice, MemberService memberService) {
        this.loginservice = loginservice;
        this.memberService = memberService;
    }

    @CrossOrigin
    @PostMapping("/auth")
    public ResponseEntity<Map<String, String>> login(@RequestBody MemberAuthDto memberAuthDto) {
        
         String token = this.loginservice.findByUsername(memberAuthDto);

        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}

