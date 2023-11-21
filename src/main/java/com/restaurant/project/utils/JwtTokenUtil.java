package com.restaurant.project.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtTokenUtil {

    /**
     * @描述: 生成token的方法
     */
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static String generateToken(String userId,String username,Long expirationMinutes) {
        //token包含三部分信息
        //头部信息
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMinutes * 60 * 1000);

        return Jwts.builder()
                .claim("userId", userId)
                .claim("username", username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }

    /**
     * @描述: 解析toke方法
     */
    public static Claims getClaimsFromToken(String token) {
        try {
            // 使用 JwtParserBuilder 构建 JwtParser
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build();
            // 使用构建的 JwtParser 解析令牌
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            return claims;
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
