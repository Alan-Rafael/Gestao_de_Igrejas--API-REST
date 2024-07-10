package com.agenda.agendaLagoinha.member.controller;

import com.agenda.agendaLagoinha.View.ViewMember;
import com.agenda.agendaLagoinha.member.MemberService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MemberViewController {

    private  final MemberService memberService;
    public MemberViewController( MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("listar")
    @JsonView({ViewMember.Base.class})
    public String getMembers(Model model){
        model.addAttribute("membros", this.memberService.ShowAllMembers());
        return "listar_membros";
    }
}
