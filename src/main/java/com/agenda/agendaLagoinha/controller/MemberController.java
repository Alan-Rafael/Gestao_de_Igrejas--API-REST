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

    private final MinistryRepository ministryRepository;
    private final MemberRepository memberRepository;
    private  final MemberService memberService;
    private  final EventRepository eventRepository;

    public MemberController(MinistryRepository ministryRepository, MemberService memberService, final EventRepository eventRepository, final MemberRepository memberRepository) {
        this.ministryRepository = ministryRepository;
        this.memberRepository = memberRepository;
        this.eventRepository = eventRepository;
        this.memberService = memberService;
    }


    @JsonView(ViewMember.Base.class)
    @PostMapping
    public ResponseEntity<Member> AddMember(@RequestBody @Validated CreateMemberRequest createMemberRequest){
        Set<Event> events = new HashSet<>(eventRepository.findAllById(createMemberRequest.getEventsId())) ;
        Set<Ministry> ministries = new HashSet<>(ministryRepository.findAllById(createMemberRequest.getMinistryId())) ;

        Member memberAux = new Member(
                null,
                createMemberRequest.getName(),
                createMemberRequest.getAge(),
                events,
                ministries,
                null
        );

        events.forEach(event -> {
            event.addMember(memberAux);
        });

       ministries.forEach(ministry -> {
                ministry.addMinistryMember(memberAux);
            });
        BeanUtils.copyProperties(createMemberRequest, memberAux);
        return ResponseEntity.ok(this.memberService.addNewMember(memberAux));
    }

    @GetMapping
    @JsonView({ViewMember.Base.class})
    public ResponseEntity<Set<Member>> getMembers(){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.memberService.ShowAllMembers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteMember(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.memberService.deleteMember(id));
    }


    @PutMapping("/{id}")
    @JsonView({ViewMember.Base.class})
    public ResponseEntity<Object> alterarMembro(@PathVariable Long id, @RequestBody UpdateMemberRequest updateMemberRequest){
        Optional<Member> member = this.memberRepository.findById(id);
        if(member.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Set<Event> events = new HashSet<>(eventRepository.findAllById(updateMemberRequest.getEventsId())) ;
        Set<Ministry> ministries = new HashSet<>(ministryRepository.findAllById(updateMemberRequest.getMinistryId())) ;

        Member memberToUpdate = member.get();

        events.forEach(event -> {
            event.addMember(memberToUpdate);
        });

        ministries.forEach(ministry -> {
            ministry.addMinistryMember(memberToUpdate);
        });
        memberToUpdate.setId(id);
        memberToUpdate.setMemberName(updateMemberRequest.getName());
        memberToUpdate.setMemberAge(updateMemberRequest.getAge());
        memberToUpdate.setEvents(events);
        memberToUpdate.setMinistries(ministries);

        return ResponseEntity.ok(memberRepository.save(memberToUpdate));
    }

    @GetMapping("/{id}")
    @JsonView({ViewMember.Admin.class})
    public ResponseEntity<Member> showOneMember(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(this.memberService.getOneMember(id));
    }
}
