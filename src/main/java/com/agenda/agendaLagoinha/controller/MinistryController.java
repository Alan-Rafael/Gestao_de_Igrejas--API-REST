package com.agenda.agendaLagoinha.controller;

import com.agenda.agendaLagoinha.View.ViewMinistry;
import com.agenda.agendaLagoinha.domain.Ministry;
import com.agenda.agendaLagoinha.member.repository.MemberRepository;
import com.agenda.agendaLagoinha.requests.CreateMinistryRequest;
import com.agenda.agendaLagoinha.requests.UpdateMinistryRequest;
import com.agenda.agendaLagoinha.service.MinistryService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/agendaLagoinha/ministry")
public class MinistryController {

    private final MemberRepository memberRepository;
    private final MinistryService ministryService;

    public  MinistryController(MemberRepository memberRepository, MinistryService ministryService){
        this.memberRepository = memberRepository;
        this.ministryService = ministryService;
    }

    @PostMapping
    @JsonView(ViewMinistry.Base.class)
    public Object create(@RequestBody CreateMinistryRequest createMinistryRequest){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(this.ministryService.insert(createMinistryRequest));
    }

    @JsonView(ViewMinistry.Base.class)
    @GetMapping
    public List<Ministry> getMinistries(){
        return this.ministryService.getAllMisitry();
    }

    @GetMapping("/{id}")
    @JsonView(ViewMinistry.Admin.class)
    public ResponseEntity<Ministry> getMinistryOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).
                body(this.ministryService.getOneMinistry(id));
    }

    @DeleteMapping("/{id}")
    @JsonView(ViewMinistry.Base.class)
    public void deleteMinistry(@PathVariable Long id){
        this.ministryService.deleteOne(id);
    }

    @PutMapping("{id}")
    @JsonView(ViewMinistry.Base.class)
    public void updateEvent(@PathVariable Long id, @RequestBody UpdateMinistryRequest updateMinistryRequest){
        this.ministryService.updateMinistry(id, updateMinistryRequest);
    }

}
