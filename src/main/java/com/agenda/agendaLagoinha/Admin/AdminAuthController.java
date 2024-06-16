package com.agenda.agendaLagoinha.Admin;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    private  final AdminAuthService adminAuthService;

    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/auth")
    public void execute(@RequestBody AdminAuthDto adminAuthDto) throws AuthenticationException {
        return;

    }
}
