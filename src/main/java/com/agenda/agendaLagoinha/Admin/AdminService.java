package com.agenda.agendaLagoinha.Admin;

import com.agenda.agendaLagoinha.security.SecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdminService {
    private final  AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    public AdminService(AdminRepository adminRepository, SecurityConfig securityConfig, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin addNewAdmin(AdminRequest adminRequest){

        var password = passwordEncoder.encode(adminRequest.getPassword());

        var admin = Admin.builder()
                .name(adminRequest.getName())
                .email(adminRequest.getEmail())
                .cpf(adminRequest.getCpf())
                .phone(adminRequest.getPhone())
                .password(password)
                .build();



        return this.adminRepository.save(admin);
    }


    public List<Admin> ShowAllAdmins(){
        List<Admin> list = new ArrayList<>();
        adminRepository.saveAll(list);
        return adminRepository.findAll();
    }

}
