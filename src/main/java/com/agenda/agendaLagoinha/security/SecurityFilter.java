package com.agenda.agendaLagoinha.security;

import com.agenda.agendaLagoinha.security.provides.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter  extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public SecurityFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,HttpServletResponse response,
            FilterChain filterChain)throws ServletException, IOException {

        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        if(header != null){
            var tokenData = this.jwtProvider.validateToken(header);
            Boolean isValid = (Boolean) tokenData.get("valid");

            if(isValid){
                String subject = (String) tokenData.get("subject");
                request.setAttribute("admin_id", subject);

                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth); 
            } else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
