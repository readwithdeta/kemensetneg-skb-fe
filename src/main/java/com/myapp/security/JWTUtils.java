package com.myapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.myapp.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JWTUtils {

    private static final String SECRET_KEY = "asdsagdyusyas1232453637eASADAD+testAPI123detaaaaboba123ahjkibaretyaww34";

    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 36000000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
