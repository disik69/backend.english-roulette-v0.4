package ua.pp.disik.englishroulette.backend.service;

import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.pp.disik.englishroulette.backend.entity.JwtToken;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenService implements Clock {
    private final String issuer;
    private final int expirationSec;
    private final int clockSkewSec;
    private final SecretKey secretKey;

    JWTTokenService(
            @Value("${jwt.issuer}") String issuer,
            @Value("${jwt.expiration-sec}") int expirationSec,
            @Value("${jwt.clock-skew-sec}") int clockSkewSec
    ) {
        this.issuer = issuer;
        this.expirationSec = expirationSec;
        this.clockSkewSec = clockSkewSec;
        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    @Override
    public Date now() {
        return DateTime.now().toDate();
    }

    public JwtToken permanent(Map<String, String> attributes) {
        return newToken(attributes, 0);
    }

    public JwtToken expiring(Map<String, String> attributes) {
        return newToken(attributes, expirationSec);
    }

    private JwtToken newToken(Map<String, String> attributes, int expirationSec) {
        DateTime now = DateTime.now();
        ClaimsBuilder claimsBuilder = Jwts
                .claims()
                .issuer(issuer)
                .issuedAt(now.toDate());

        if (expirationSec > 0) {
            final DateTime expiresAt = now.plusSeconds(expirationSec);
            claimsBuilder.expiration(expiresAt.toDate());
        }
        claimsBuilder.add(attributes);

        String token = Jwts
                .builder()
                .claims(claimsBuilder.build())
                .signWith(secretKey)
                .compressWith(Jwts.ZIP.GZIP)
                .compact();

        return new JwtToken(token);
    }

    public Map<String, String> verify(JwtToken token) {
        JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .clock(this)
                .clockSkewSeconds(clockSkewSec)
                .verifyWith(secretKey)
                .build();
        try {
            Claims claims = parser.parseSignedClaims(token.getToken()).getPayload();
            Map<String, String> body = new HashMap<>();
            for (final Map.Entry<String, Object> e : claims.entrySet()) {
                body.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return body;
        } catch (IllegalArgumentException | JwtException e) {
            return new HashMap<>();
        }
    }
}
