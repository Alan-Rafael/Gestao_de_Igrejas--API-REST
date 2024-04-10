package com.agenda.agendaLagoinha.service;


import com.agenda.agendaLagoinha.Exception.MemberNotFoundException;
import com.agenda.agendaLagoinha.domain.Event;
import com.agenda.agendaLagoinha.domain.Member;
import com.agenda.agendaLagoinha.domain.Ministry;
import com.agenda.agendaLagoinha.repository.MemberRepository;
import com.agenda.agendaLagoinha.repository.MinistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;
    private final MinistryRepository ministryRepository;

    public MemberService(MemberRepository memberRepository, MinistryRepository ministryRepository) {
        this.memberRepository = memberRepository;
        this.ministryRepository = ministryRepository;
    }

    public Member addNewMember(Member member){
        return this.memberRepository.save(member);
    }

    public ResponseEntity<Object> deleteMember(Long id){
        Member member = memberRepository.findById(id).
                orElseThrow(() -> new MemberNotFoundException("Membro não encontrado com o ID:"));;
        if (member != null) {
            for (Event evento : member.getEvents()) {
                evento.getEventMembers().remove(member);
            }
            for (Ministry ministry: member.getMinistries()){
                ministry.getMinistryMembers().remove(member);
            }

            Set<Ministry> ministeriosQueSouLider = member.getLiderando();
            if (!ministeriosQueSouLider.isEmpty()) {
                for (Ministry ministry : ministeriosQueSouLider) {
                    ministry.setLeader(null);
                    ministryRepository.save(ministry);
                }
            }

            this.memberRepository.deleteById(id);

        }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

     public Set<Member> ShowAllMembers(){
         return new HashSet<>(memberRepository.findAll());
    }

    public Member getOneMember(Long id){
        return this.memberRepository.findById(id).
                orElseThrow(() -> new MemberNotFoundException("Membro não encontrado"));
    }

}
