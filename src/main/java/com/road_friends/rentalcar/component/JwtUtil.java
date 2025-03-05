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
  @Value("${jwt.secret-key}")
  private String SECRET_KEY;
  @Value("${jwt.expiration-time}")// 보안성을 위해 환경변수로 관리 권장
  private long EXPIRATION_TIME;    // 1일 (ms)

  // JWT 토큰 생성
  public String generateToken(String userID, List<String> roles) {
    return Jwts.builder()
            .setSubject(userID)
            .claim("roles", roles) // 권한 정보 추가
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
  }

  // JWT 토큰에서 사용자 정보 추출
  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  // 권한(roles) 정보 추출
  public List<String> extractRoles(String token) {
    return extractClaims(token).get("roles", List.class);
  }

  // 토큰 유효성 검증
  public boolean validateToken(String token) {
    return !extractClaims(token).getExpiration().before(new Date());
  }

  // Claims 추출
  private Claims extractClaims(String token) {
    return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();
  }

  // 면허증 정보 입력 후 권한 업데이트 및 새로운 JWT 토큰 발급
  public String generateTokenWithUpdatedRole(String userId, List<String> roles) {
    // 기존의 'roles' 리스트에 'ROLE_VERIFIED' 권한을 추가
    if (!roles.contains("ROLE_VERIFIED")) {
      roles.add("ROLE_VERIFIED");
    }

    // 새로운 JWT 토큰 생성
    return generateToken(userId, roles);
  }

}
