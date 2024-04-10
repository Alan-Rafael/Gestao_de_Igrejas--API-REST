package com.agenda.agendaLagoinha.service;


import com.agenda.agendaLagoinha.Exception.MinistryNotFoundException;
import com.agenda.agendaLagoinha.domain.Member;
import com.agenda.agendaLagoinha.domain.Ministry;
import com.agenda.agendaLagoinha.repository.MemberRepository;
import com.agenda.agendaLagoinha.repository.MinistryRepository;
import com.agenda.agendaLagoinha.requests.UpdateMinistryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinistryService {


    private final MinistryRepository ministryRepository;
    private final MemberRepository memberRepository;

    public MinistryService (MinistryRepository ministryRepository, MemberRepository memberRepository){
        this.ministryRepository = ministryRepository;
        this.memberRepository = memberRepository;
    }



    public Ministry insert(Ministry ministry){
        return this.ministryRepository.save(ministry);
    }

    public Ministry updateMinistry(Long id, UpdateMinistryRequest ministryToUpdate){
        Ministry ministry = this.ministryRepository.findById(id).
                orElseThrow(()-> new MinistryNotFoundException("Ministry Not Found"));
        List<Member> leader = this.memberRepository.findAllById(ministryToUpdate.getLider());
        Member memberLeader = leader.get(0);

        ministry.setName(ministryToUpdate.getName());
        ministry.setLeader(memberLeader);
        this.ministryRepository.save(ministry);
        return ministry;

    }
}
