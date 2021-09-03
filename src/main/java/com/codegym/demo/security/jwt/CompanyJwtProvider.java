package com.codegym.demo.security.jwt;

import com.codegym.demo.security.userprincipal.CompanyPrinciple;
import com.codegym.demo.security.userprincipal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CompanyJwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(CompanyJwtProvider.class);
    private static final String JWT_SECRET = "tiennv@gmail.com";
    private static final int JWT_EXPIRATION = 86400;

    public String createToken(Authentication authentication) {
        CompanyPrinciple companyPrinciple = (CompanyPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(companyPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {}", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid format Token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty --> Message {}", e);
        }
        return false;
    }

    public String getEmailFromToken(String token) {
        String email = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
        return email;
    }
}
