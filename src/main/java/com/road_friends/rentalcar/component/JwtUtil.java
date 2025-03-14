package com.road_friends.rentalcar.component;


import com.road_friends.rentalcar.dto.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

  private final SecretKey secretKey;
  private final long EXPIRATION_TIME;
  private final long REFRESH_EXPIRATION_TIME;

  public JwtUtil(@Value("${jwt.secret-key}") String secretKeyString,
                 @Value("${jwt.expiration-time}") long expirationTime,
                 @Value("${jwt.refresh-expiration-time}") long refreshExpirationTime) {
    // ✅ Base64 디코딩 후 SecretKey 생성
    byte[] keyBytes = Base64.getDecoder().decode(secretKeyString);
    this.secretKey = Keys.hmacShaKeyFor(keyBytes);

    this.EXPIRATION_TIME = expirationTime;
    this.REFRESH_EXPIRATION_TIME = refreshExpirationTime;
  }

  // ✅ JWT 토큰 생성 (SecretKey 사용)
  public String generateToken(String userId, Long userNum, List<String> roles) {
    return Jwts.builder()
            .setSubject(userId)
            .claim("user_num", userNum)
            .claim("roles", roles)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(secretKey, SignatureAlgorithm.HS256) // ✅ SecretKey 적용
            .compact();
  }

  // ✅ 리프레시 토큰 생성
  public String generateRefreshToken(String userId, Long userNum) {
    return Jwts.builder()
            .setSubject(userId)
            .claim("user_num", userNum)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
            .signWith(secretKey, SignatureAlgorithm.HS256) // ✅ SecretKey 적용
            .compact();
  }

  // ✅ 토큰에서 클레임(Claims) 추출
  private Claims extractClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  // ✅ 토큰에서 유저 정보 추출
  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  // ✅ 토큰에서 user_num 추출
  public Long extractUserNum(String token) {
    return extractClaims(token).get("user_num", Long.class);
  }

  // ✅ 토큰에서 권한(roles) 추출
  public List<String> extractRoles(String token) {
    return extractClaims(token).get("roles", List.class);
  }

  // ✅ 토큰 유효성 검증
  public boolean validateToken(String token) {
    return !extractClaims(token).getExpiration().before(new Date());
  }
}