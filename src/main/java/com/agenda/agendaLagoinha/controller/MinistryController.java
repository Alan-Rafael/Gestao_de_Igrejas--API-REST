package com.agenda.agendaLagoinha.controller;

import com.agenda.agendaLagoinha.Exception.MemberNotFoundException;
import com.agenda.agendaLagoinha.View.ViewMinistry;
import com.agenda.agendaLagoinha.domain.Member;
import com.agenda.agendaLagoinha.domain.Ministry;
import com.agenda.agendaLagoinha.repository.MemberRepository;
import com.agenda.agendaLagoinha.repository.MinistryRepository;
import com.agenda.agendaLagoinha.requests.CreateMinistryRequest;
import com.agenda.agendaLagoinha.requests.UpdateMinistryRequest;
import com.agenda.agendaLagoinha.service.MinistryService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/agendaLagoinha/ministry")
public class MinistryController {

    private final MemberRepository memberRepository;
    private final MinistryRepository ministryRepository;

    private final MinistryService ministryService;

    public  MinistryController(MemberRepository memberRepository, MinistryService ministryService, MinistryRepository ministryRepository){
        this.memberRepository = memberRepository;
        this.ministryRepository = ministryRepository;
        this.ministryService = ministryService;
    }

    @PostMapping
    @JsonView(ViewMinistry.Base.class)
    public ResponseEntity<Ministry > create(@RequestBody CreateMinistryRequest createMinistryRequest){
        Member lieder = memberRepository.findById(createMinistryRequest.getLeaderId()).
                orElseThrow(() -> new MemberNotFoundException("Member Not Found"));

        final Ministry ministry = new Ministry(
                null,
                createMinistryRequest.getName(),
                lieder,
                Collections.emptySet()
        );

        lieder.addMinisterioQueSouLider(ministry);
        BeanUtils.copyProperties(createMinistryRequest, ministry);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ministryService.insert(ministry));
    }

    @JsonView(ViewMinistry.Base.class)
    @GetMapping
    public List<Ministry> getMinistries(){
        return this.ministryRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(ViewMinistry.Admin.class)
    public ResponseEntity<Optional<Ministry>> getMinistryOne(@PathVariable Long id){
        Optional<Ministry> ministryAux = this.ministryRepository.findById(id);
        if(ministryAux.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(ministryAux);
    }

    @DeleteMapping("/{id}")
    public void deleteMinistry(@PathVariable Long id){
        this.ministryRepository.deleteById(id);
    }


    @PutMapping("{id}")
    @JsonView(ViewMinistry.Base.class)
    public ResponseEntity<Object> updateEvent(@PathVariable Long id, @RequestBody UpdateMinistryRequest updateMinistryRequest){

        return ResponseEntity.status(HttpStatus.OK).body(this.ministryService.updateMinistry(id, updateMinistryRequest));
    }

}
