package com.example.nlu.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY_STRING = "tominhnhat21130463some_long_string_for_security"; // Đặt khóa bí mật cố định
    private static final Key SECRET_KEY = new SecretKeySpec(SECRET_KEY_STRING.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    public static String generateToken(long userID) {
        return Jwts.builder()
                .setSubject(String.valueOf(userID))
                .claim("id", userID)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))  // token het
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();  // Trả về chuỗi token
    }

    public static boolean validateToken(String token) {
//            return true;
        token =token.replace("Bearer ", "");

        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)  // Đặt khóa bí mật để xác minh chữ ký
                    .build()
                    .parseClaimsJws(token);  // Giải mã token và kiểm tra tính hợp lệ của nó
            return true;  // Token hợp lệ
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;  // Token không hợp lệ hoặc đã hết hạn
        }
    }

    public static void main(String[] args) {
        String token = JwtUtils.generateToken(1);
        System.out.println(token);
        token ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWQiOjEsImlhdCI6MTczNjk2NzI4NywiZXhwIjoxNzM3MDUzNjg3fQ.CYsaY6MvbQ-bPjzdbhUfeT83DNZfRLCcyGjIElDXCU0";
        if(!JwtUtils.validateToken(token)) {
            System.out.println("token het han");
        } else {
            System.out.println("ok");
        }
    }
}
