package com.agenda.agendaLagoinha.Admin;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminService {
    private final  AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin addNewAdmin(AdminRequest adminRequest){
        var admin = Admin.builder()
                .name(adminRequest.getName())
                .Email(adminRequest.getEmail())
                .cpf(adminRequest.getCpf())
                .phone(adminRequest.getPhone())
                .password(adminRequest.getPassword())
                .build();

        return this.adminRepository.save(admin);
    }


    public List<Admin> ShowAllAdmins(){
        List<Admin> list = new ArrayList<>();
        adminRepository.saveAll(list);
        return adminRepository.findAll();
    }

}
