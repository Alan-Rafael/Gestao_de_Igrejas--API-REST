package com.agenda.agendaLagoinha.service;


import com.agenda.agendaLagoinha.member.exception.MemberNotFoundException;
import com.agenda.agendaLagoinha.Exception.MinistryNotFoundException;
import com.agenda.agendaLagoinha.member.domain.Member;
import com.agenda.agendaLagoinha.domain.Ministry;
import com.agenda.agendaLagoinha.member.repository.MemberRepository;
import com.agenda.agendaLagoinha.repository.MinistryRepository;
import com.agenda.agendaLagoinha.requests.CreateMinistryRequest;
import com.agenda.agendaLagoinha.requests.UpdateMinistryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MinistryService {

    private final MinistryRepository ministryRepository;
    private final MemberRepository memberRepository;

    public MinistryService (MinistryRepository ministryRepository, MemberRepository memberRepository){
        this.ministryRepository = ministryRepository;
        this.memberRepository = memberRepository;
    }

    public Ministry insert(CreateMinistryRequest createMinistryRequest){
        Member lieder = memberRepository.findByCpf(createMinistryRequest.getLeader());
        if(lieder==null){
            throw new MemberNotFoundException();
        }
        Set<Member> listadeMembros = new HashSet<>(memberRepository.findByCpfIn(createMinistryRequest.getMembers()));
        final Ministry ministry = new Ministry(
                null,
                createMinistryRequest.getName(),
                lieder,
                listadeMembros
        );
        lieder.addMinisterioQueSouLider(ministry);
        return this.ministryRepository.save(ministry);
    }

    public ResponseEntity<Member> updateMinistry(Long id, UpdateMinistryRequest ministryToUpdate){

        Ministry ministry = this.ministryRepository.findById(id).
                orElseThrow(MinistryNotFoundException::new);

        Member lider = memberRepository.findByCpf(ministryToUpdate.getLeader());

        Set<Member> members = new HashSet<>(memberRepository.findByCpfIn(ministryToUpdate.getMembers()));

        ministry.setName(ministryToUpdate.getName());
        ministry.setLeader(lider);
        ministry.setMinistryMembers(members);

        this.ministryRepository.save(ministry);
        return ResponseEntity.status(HttpStatus.OK).body(lider);
    }

    public Ministry getOneMinistry(Long id){
        return  this.ministryRepository.findById(id).
                orElseThrow(MinistryNotFoundException::new);
    }

    public void deleteOne(Long id){
        Ministry ministry = this.ministryRepository.findById(id).
                orElseThrow(MinistryNotFoundException::new);
        this.ministryRepository.deleteById(id);
    }

    public List<Ministry> getAllMisitry(){
        return ministryRepository.findAll();
    }
}
