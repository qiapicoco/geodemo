package com.qiapicoco.geodemo.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void testGenerateToken() {
        String username = "testUser";
        String token = jwtUtils.generateToken(username);
        assertNotNull(token);
        // 简单验证生成的 JWT 是否符合基本格式
        String subject = Jwts.parser()
               .setSigningKey("your_jwt_secret")
               .parseClaimsJws(token)
               .getBody()
               .getSubject();
        assertEquals(username, subject);
    }
}