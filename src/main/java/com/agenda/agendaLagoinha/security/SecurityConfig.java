package com.agenda.agendaLagoinha.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    private static final String [] ROTAS_IGREJAS = {
            "swagger-ui/**",
            "/v3/api-docs/**",
            "swagger-resources/**"
    };

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/churchManagement/member").permitAll()
                            .requestMatchers("/admin").permitAll()
                            .requestMatchers("/admin/auth").permitAll()
                            .requestMatchers("/churchManagement/event/getAll").permitAll()
                            .requestMatchers("/loginMembers/entrar").permitAll()
                            .requestMatchers(ROTAS_IGREJAS).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
