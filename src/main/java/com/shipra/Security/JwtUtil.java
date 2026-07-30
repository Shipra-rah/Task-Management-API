package com.shipra.Security;

import com.shipra.Domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final String SECRATE_KEY = "your_secret_key_keyhereyour_secret_key_keyhere";
    private SecretKey keys = Keys.hmacShaKeyFor(SECRATE_KEY.getBytes());

    public String genrateToken(String email, Role role){
        return Jwts.builder()
                .subject(email)
                .issuer("Shipra")
                .claim("Role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ 24*60*60*60))
                .signWith(keys)
                .compact();
    }

    public boolean isValidToken(String token){
        return getClaims(token).getExpiration().after(new Date());
    }

    public String extractEmail(String token){
        return   getClaims(token).getSubject();
    }

    public Role getRole(String token){
        String role = getClaims(token).get("Role", String.class);
        return Role.valueOf(role);
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(keys)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
