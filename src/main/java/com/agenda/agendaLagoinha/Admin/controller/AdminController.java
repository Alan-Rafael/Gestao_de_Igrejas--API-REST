package com.agenda.agendaLagoinha.Admin.controller;

import com.agenda.agendaLagoinha.Admin.Admin;
import com.agenda.agendaLagoinha.Admin.AdminRequest;
import com.agenda.agendaLagoinha.Admin.AdminService;
import com.agenda.agendaLagoinha.View.ViewMember;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping
    @JsonView({ViewMember.Admin.class})
    public ResponseEntity<Admin> add(@RequestBody @Valid AdminRequest adminRequest){
        return ResponseEntity.ok().body(service.addNewAdmin(adminRequest));
    }

    @GetMapping
    @JsonView({ViewMember.Base.class})
    public ResponseEntity<List<Admin>> returnAll(){
        return ResponseEntity.ok().body(service.ShowAllAdmins());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Optional<Admin>> buscarUmAdm(@PathVariable String email ){
        return ResponseEntity.ok().body(service.pegarUmAdm(email));
    }
}
