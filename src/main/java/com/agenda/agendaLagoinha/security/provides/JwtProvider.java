package com.agenda.agendaLagoinha.security.provides;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtProvider {

    @Value("java@123#")
    private String secretKey;

    public Map<String, Object> validateToken(String token) {
        
        Map<String, Object> response = new HashMap<>();
        
        token = token.replace("Bearer ", "").trim();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            response.put("valid", true);
            response.put("subject", decodedJWT.getSubject());
            response.put("issuedAt", decodedJWT.getIssuedAt());
            response.put("expiresAt", decodedJWT.getExpiresAt());
        } catch (JWTVerificationException exception) {
            response.put("valid", false);
            response.put("error", exception.getMessage());
        }
        return response;
    }
}
