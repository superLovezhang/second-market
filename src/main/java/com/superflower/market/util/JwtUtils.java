package com.superflower.market.util;

import com.superflower.market.entity.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String salt = "superflower";

    public static String encode(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        Date expire = new Date(currentTimeMillis + (1000 * 60 * 60 * 5));
        return Jwts.builder()
                .setExpiration(expire)
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .signWith(SignatureAlgorithm.HS256, salt)
                .compact();
    }

    public static boolean checkToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(salt)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Map decode(String token) {
        return Jwts.parser()
                .setSigningKey(salt)
                .parseClaimsJws(token)
                .getBody();
    }

}
