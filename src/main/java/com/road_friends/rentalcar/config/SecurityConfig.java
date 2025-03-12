package com.road_friends.rentalcar.config;

import com.road_friends.rentalcar.component.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserDetailsService userDetailsService;

  public SecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Bean
  @Order(1)
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info("security config ...");

    http
            .securityMatcher("/admin/**")
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                    // 리애트 서버의 요청 관리
                    // TODO 특정 리액트 서버만 접속 허용 또는 내부 네트워크만 접속 허용
                    .requestMatchers("/api/**").permitAll()

                    // 관리자 요청 관리
                    .requestMatchers("/admin/login", "/admin").permitAll()
                    .requestMatchers("/admin/user/{id}").hasRole("ADMIN")
                    .requestMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER")

                    // 그 외는 인증을 요구
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                    .loginPage("/admin/login")
                    .usernameParameter("userId")  // username 대신 user_id로 변경
                    .passwordParameter("userPassword")  // password 대신 user_password로 변경
                    .defaultSuccessUrl("/admin/dashboard", true)
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutSuccessUrl("/admin")
                    .permitAll()
            );

    return http.build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

    http
            .securityMatcher("/api/**")  // 모든 /api/** 경로에 대해 보안 설정
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless 환경
            )
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/public/**").permitAll() // 공개 API에 대해 접근 허용
                    .requestMatchers("/api/auth/login", "/api/auth/signup").permitAll() // 로그인, 회원가입 API 허용
                    .requestMatchers("/api/quick-rent/cars/**").permitAll() // 차량 조회 페이지 접근 허용
                    .requestMatchers("/api/short-rent/cars/**").permitAll() // 단기 대여 차량 조회 페이지 접근 허용

                    // 특정 경로에 대해서만 권한 설정
                    .requestMatchers("/api/quick-rent/reservations/**").hasAuthority("ROLE_VERIFIED") // ROLE_VERIFIED 권한이 있어야 접근 가능
                    .requestMatchers("/api/short-rent/reservations").hasAuthority("ROLE_VERIFIED") // ROLE_VERIFIED 권한이 있어야 접근 가능

                    // 나머지 경로는 인증 필요
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가
            .logout(AbstractHttpConfigurer::disable);

    return http.build();
  }


  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

