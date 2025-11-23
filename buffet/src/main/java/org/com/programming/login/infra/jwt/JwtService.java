package org.com.programming.login.infra.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "f47d1fb5d8bb9c386302d2920843bde7ab4093921590bda77fe0361c27aff9a6"; /* Envelopar em um lugar seguro. */

    public String extractNameUser(String tokenUsuario){
        return extractInformationClaim(tokenUsuario, Claims::getSubject);
    }

    public <T> T extractInformationClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllTheClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Objects> extractClaim, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extractClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(signature(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean ifValidToken(String token, UserDetails userDetails){
        final String username = extractNameUser(token);
        return (username.equals(userDetails.getUsername()) && !ifTokenExpirado(token));
    }
    private boolean ifTokenExpirado(String token){
        return extractToken(token).before(new Date());
    }

    private Date extractToken(String token){
        return extractInformationClaim(token, Claims::getExpiration);
    }

    private Claims extractAllTheClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(signature())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key signature() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
