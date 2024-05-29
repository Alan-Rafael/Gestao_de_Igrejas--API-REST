package com.agenda.agendaLagoinha.controller;


import com.agenda.agendaLagoinha.View.ViewMember;
import com.agenda.agendaLagoinha.View.ViewMinistry;
import com.agenda.agendaLagoinha.domain.Event;
import com.agenda.agendaLagoinha.domain.Member;
import com.agenda.agendaLagoinha.domain.Ministry;
import com.agenda.agendaLagoinha.repository.EventRepository;
import com.agenda.agendaLagoinha.repository.MemberRepository;
import com.agenda.agendaLagoinha.repository.MinistryRepository;
import com.agenda.agendaLagoinha.requests.CreateMemberRequest;
import com.agenda.agendaLagoinha.requests.UpdateMemberRequest;
import com.agenda.agendaLagoinha.service.MemberService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/agendaLagoinha/member")
public class MemberController {

    private  final MemberService memberService;

    public MemberController( MemberService memberService) {
        this.memberService = memberService;
    }


    @JsonView(ViewMember.Base.class)
    @PostMapping
    public ResponseEntity<Member> AddMember(@RequestBody @Valid CreateMemberRequest createMemberRequest){

        return ResponseEntity.ok(this.memberService.addNewMember(createMemberRequest));
    }

    @GetMapping
    @JsonView({ViewMember.Base.class})
    public ResponseEntity<Set<Member>> getMembers(){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.memberService.ShowAllMembers());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object>deleteMember(@PathVariable String cpf){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.memberService.deleteMember(cpf));
    }

    @PutMapping("/{cpf}")
    @JsonView({ViewMember.Base.class})
    public ResponseEntity<Object> alterarMembro(@PathVariable String cpf, @RequestBody UpdateMemberRequest updateMemberRequest){
        return ResponseEntity.ok(memberService.update(cpf, updateMemberRequest));

    }

    @GetMapping("/{cpf}")
    @JsonView({ViewMember.Admin.class})
    public Member showOneMember(@PathVariable String cpf){
        return this.memberService.getOneMember(cpf);
    }

}
