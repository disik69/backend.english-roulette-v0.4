package ua.pp.disik.englishroulette.backend.services;

import io.jsonwebtoken.Clock;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTTokenService implements Clock {
    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();

    private final String issuer;
    private final int expirationSec;
    private final int clockSkewSec;
    private final String secretKey;

    JWTTokenService(@Value("${jwt.issuer}") String issuer,
                    @Value("${jwt.expiration-sec}") int expirationSec,
                    @Value("${jwt.clock-skew-sec}") int clockSkewSec,
                    @Value("${jwt.secret-key}") String secretKey) {
        this.issuer = issuer;
        this.expirationSec = expirationSec;
        this.clockSkewSec = clockSkewSec;
        this.secretKey = secretKey;
    }

    @Override
    public Date now() {
        return DateTime.now().toDate();
    }
}
