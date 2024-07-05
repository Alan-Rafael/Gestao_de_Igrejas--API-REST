package com.agenda.agendaLagoinha.member;


import com.agenda.agendaLagoinha.member.exception.MemberExistException;
import com.agenda.agendaLagoinha.member.exception.MemberNotFoundException;
import com.agenda.agendaLagoinha.event.Event;
import com.agenda.agendaLagoinha.ministerios.Ministry;
import com.agenda.agendaLagoinha.ministerios.MinistryRepository;
import com.agenda.agendaLagoinha.requests.CreateMemberRequest;
import com.agenda.agendaLagoinha.requests.UpdateMemberRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MinistryRepository ministryRepository;

    final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, MinistryRepository ministryRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.ministryRepository = ministryRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member addNewMember(CreateMemberRequest createMemberRequest){

        Member CpfJaExiste = memberRepository.findByCpf(createMemberRequest.getCpf());
        Optional<Member> EmailJaExiste = memberRepository.findByEmail(createMemberRequest.getEmail());

        if ((CpfJaExiste!= null) || (EmailJaExiste.isPresent())){
            throw new MemberExistException();
        }

        var passwordCript = passwordEncoder.encode(createMemberRequest.getPassword());
        createMemberRequest.setPassword(passwordCript);
        var member = new Member(
                null,
                createMemberRequest.getName(),
                createMemberRequest.getCpf(),
                createMemberRequest.getEmail(),
                createMemberRequest.getAge(),
                createMemberRequest.getSexo(),
                passwordCript
        );
        return  this.memberRepository.save(member);
    }


    @Transactional
    public ResponseEntity<Object> deleteMember(String cpf){
        Member member = memberRepository.findByCpf(cpf);
        if (member!=null) {
            for (Event event : member.getEvents()) {
                event.getEventMembers().remove(member);
            }
            for (Ministry ministry: member.getMinistries()){
                ministry.getMinistryMembers().remove(member);
            }
            Set<Ministry> ministeriosQueSouLider = member.getLiderando();
            if (!ministeriosQueSouLider.isEmpty()) {
                for (Ministry ministry : ministeriosQueSouLider) {
                    ministry.setLeader(null);
                    ministryRepository.save(ministry);}
            }this.memberRepository.deleteByCpf(cpf);
        }else {
            throw new MemberNotFoundException();
        }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

     public Set<Member> ShowAllMembers(){
         return new HashSet<>(memberRepository.findAll());
    }

    public Member getOneMember(String cpf){
        return this.memberRepository.findByCpf(cpf);
    }

    public Member update(String cpf, UpdateMemberRequest member){
        Member memberToUpdate = memberRepository.findByCpf(cpf);
        if(memberToUpdate == null){
           throw new MemberNotFoundException();
        }
        memberToUpdate.setName(member.getName());
        memberToUpdate.setAge(member.getAge());
        return memberRepository.save(memberToUpdate);

    }


}
