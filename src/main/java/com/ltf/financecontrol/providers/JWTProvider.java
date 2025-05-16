package com.ltf.financecontrol.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var tokenDecoded = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return tokenDecoded;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }

    }

}
