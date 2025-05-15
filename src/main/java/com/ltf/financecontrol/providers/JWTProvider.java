package com.ltf.financecontrol.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    @Value("${security.token.issuer}")
    private String issuer;

    public String generateTokenToSubject(
            String subject,
            Instant expiresIn
    ) {
        return JWT
                .create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(expiresIn)
                .sign(Algorithm.HMAC256(secretKey));
    }

}
