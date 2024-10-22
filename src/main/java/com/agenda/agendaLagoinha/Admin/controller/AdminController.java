package com.agenda.agendaLagoinha.Admin.controller;

import com.agenda.agendaLagoinha.Admin.Admin;
import com.agenda.agendaLagoinha.Admin.AdminRequest;
import com.agenda.agendaLagoinha.Admin.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Admin> add(@RequestBody @Valid AdminRequest adminRequest){
        return ResponseEntity.ok().body(service.addNewAdmin(adminRequest));

    }

    @GetMapping
    public ResponseEntity<List<Admin>> returnAll(){
        return ResponseEntity.ok().body(service.ShowAllAdmins());
    }
}
