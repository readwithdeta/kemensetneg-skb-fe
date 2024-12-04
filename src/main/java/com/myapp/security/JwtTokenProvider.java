//package com.myapp.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    private final String JWT_SECRET = "secretKey"; // Gunakan secret key yang lebih kuat dan aman
//    private final long JWT_EXPIRATION = 604800000L; // Token kedaluwarsa dalam 7 hari (dalam milidetik)
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
//                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//                .compact();
//    }
//
//    public String getUsernameFromToken(String token) {
//        return getClaimsFromToken(token).getSubject();
//    }
//
//    public Claims getClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(JWT_SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            return !getClaimsFromToken(token).getExpiration().before(new Date());
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public UserDetails getUserDetailsFromToken(String token) {
//        String username = getUsernameFromToken(token);
//        return new User(username, "", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
//    }
//
//    public String getAuthorities(String token) {
//        return getClaimsFromToken(token).get("roles", String.class);
//    }
//}
