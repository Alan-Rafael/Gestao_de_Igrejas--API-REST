package com.agenda.agendaLagoinha.member;


import com.agenda.agendaLagoinha.View.ViewMember;
import com.agenda.agendaLagoinha.requests.MemberRequests.CreateMemberRequest;
import com.agenda.agendaLagoinha.requests.MemberRequests.UpdateMemberRequest;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    private  final MemberService memberService;

    public MemberController( MemberService memberService) {
        this.memberService = memberService;
    }


    @JsonView(ViewMember.Base.class)
    @PostMapping("/cadastrar")
    public ResponseEntity<Member> cadastrarMembro(@RequestBody CreateMemberRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.memberService.addNewMember(request));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object>deleteMember(@PathVariable String cpf){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).
                body(this.memberService.deleteMember(cpf));
    }

    @PutMapping("/{cpf}")
    @JsonView({ViewMember.Base.class})
    public ResponseEntity<Object> alterarMembro(@PathVariable String cpf, @RequestBody UpdateMemberRequest updateMemberRequest){
        return ResponseEntity.
                ok(memberService.update(cpf, updateMemberRequest));

    }

    @CrossOrigin
    @GetMapping("/listar")
    @JsonView({ViewMember.Base.class})
    public ResponseEntity<Set<Member>> getMembers(){
        return ResponseEntity.ok().body(memberService.ShowAllMembers());
    }

    @GetMapping("/getbyCpf/{cpf}")
    @JsonView({ViewMember.Admin.class})
    public Member showOneMember(@PathVariable String cpf){
        return this.memberService.getOneMember(cpf);
    }

    @GetMapping("/getbyId/{id}")
    public Optional<Member> getById(@RequestParam Long id) {
        return this.memberService.pegarPeloId(id);
    }
    



}
