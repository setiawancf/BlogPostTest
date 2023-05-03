package com.sigma.BlogPostTest.security;

import com.sigma.BlogPostTest.vo.UserInfoVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtToken {
    @Value("${jwt.token.secret}")
    private String secret;

    public String generateToken(UserInfoVo jwtUser) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
//        claims.put("email", jwtUser.getEmail());

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(2l, ChronoUnit.HOURS)))
                .signWith(hmacKey).compact();

        return jwtToken;
    }

    public Jws<Claims> parseJwt(String jwtString) throws Exception {
        try {
            Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                    SignatureAlgorithm.HS256.getJcaName());
            Jws<Claims> jwt = Jwts.parserBuilder()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(jwtString);

            return jwt;
        } catch (Exception e) {
            log.error("JWT Token is incorrect");
        }
        return null;
    }

    public UserInfoVo validate(String token) {
        UserInfoVo jwtUser = null;
        try {
            Claims body = parseJwt(token).getBody();

            jwtUser = new UserInfoVo();
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setName(body.getSubject());

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            return jwtUser;
        }
    }
}
