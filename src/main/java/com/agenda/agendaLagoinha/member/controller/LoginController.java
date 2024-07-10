package com.agenda.agendaLagoinha.member.controller;

import com.agenda.agendaLagoinha.member.Member;
import com.agenda.agendaLagoinha.member.MemberAuthDto;
import com.agenda.agendaLagoinha.member.MemberService;
import com.agenda.agendaLagoinha.member.Sexo;
import com.agenda.agendaLagoinha.member.controller.services.MemberLoginService;

import com.agenda.agendaLagoinha.requests.CreateMemberRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/loginMember")
public class LoginController {

    final MemberLoginService service;
    final MemberService memberService;

    public LoginController(MemberLoginService service, MemberService memberService) {
        this.service = service;
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String seila() {
        return "membro_login";
    }

    @GetMapping("/voltarProfile")
    public String seila34() {
        return "membro_profile";
    }



    @GetMapping("/cadastrarPage")
    public String seila2() {
        return "membro_cadastro";
    }

    @PostMapping("/auth")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

        MemberAuthDto membro = new MemberAuthDto(email, password);
        String member = service.findByUsername(membro);
        if (member != null ){
            Member member1 = this.service.findMembro(email);
            session.setAttribute("loggedInMember", member1);
            model.addAttribute("member", member1);
            System.out.println("entrou no if main");
            return "redirect:/loginMember/getProfile";
        } else {
            System.out.println("entrou no else Main");
            model.addAttribute("error", "errou");
            return "redirect:/loginMember/login";
        }
    }

    @PostMapping("/cadastrar")
    public String cadastrarMembro(
                                  @RequestParam String nome,
                                  @RequestParam String cpf,
                                  @RequestParam String email,
                                  @RequestParam Long idade,
                                  @RequestParam String senha,
                                  @RequestParam Sexo sexo ){
        var request = new CreateMemberRequest
                (nome, cpf, email,idade, sexo, senha);
        this.memberService.addNewMember(request);
        return  "redirect:/loginMember/cadastrar";

    }

    @GetMapping("/getProfile")
    public String perfilzin(Model model, HttpSession session) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");
        if (loggedInMember != null) {
            model.addAttribute("member", loggedInMember);
            System.out.println("entrou no if getprofile");

            return "membro_profile";
        } else {
            System.out.println("entrou no else getprofile");

            return "redirect:/loginMember/login";
        }
    }
}

