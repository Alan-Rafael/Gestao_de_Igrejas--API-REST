package com.agenda.agendaLagoinha.Admin;

import com.agenda.agendaLagoinha.security.SecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService {
    private final  AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    public AdminService(AdminRepository adminRepository, SecurityConfig securityConfig, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin addNewAdmin(AdminRequest adminRequest){
        Optional<Admin> admins= this.adminRepository.findByEmail(adminRequest.getEmail());
        if(admins.isEmpty()){
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
       return null;
    }


    public List<Admin> ShowAllAdmins(){
        List<Admin> list = new ArrayList<>();
        adminRepository.saveAll(list);
        return adminRepository.findAll();
    }

    public Optional<Admin> pegarUmAdm(String email){
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if(admin.isPresent()){
            return admin;
        }
        return null;
    }


}
