package com.road_friends.rentalcar.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
  private final String userId;
  private final String userPassword;
  private final List<GrantedAuthority> authorities;

  public CustomUserDetails(String userId, String userPassword, List<String> roles) {
    this.userId = userId;
    this.userPassword = userPassword;
    this.authorities = roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return userPassword;  // 비밀번호 추가
  }

  @Override
  public String getUsername() {
    return userId;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
