package com.agenda.agendaLagoinha.provides;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtProvider {

    @Value("java@123#")
    private String secretKey;

    public String validateToken(String token){
        token = token.replace("Bearer", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
            return subject;
        }catch (JWTVerificationException exception){
            exception.printStackTrace();
            return "";
        }
    }
}
