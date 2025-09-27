package com.example.projectSpring1.configuration;

import com.example.projectSpring1.dto.response.JwtUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    public  String generationToken(String username,Long id,String role){
        return Jwts.builder().setSubject(username).claim("id",id).claim("role", role)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256).compact();

    }




   public JwtUserInfo getUserInfoFromToken(String token){
       Claims claims = Jwts.parserBuilder()
               .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
               .build()
               .parseClaimsJws(token)
               .getBody();
       Long id = claims.get("id", Long.class);
       String username = claims.getSubject();
       String role = claims.get("role",String.class);
       return  new JwtUserInfo(id,username,role);
   }

   public  Long getIdFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
       Long id = claims.get("id", Long.class);
       return id;
   }
}
