package com.road_friends.rentalcar.service;

import java.util.List;
import java.util.stream.Collectors;

import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
    UserDTO userDTO = userMapper.findByUsername(user_name);
    if (userDTO == null) {
      throw new UsernameNotFoundException("User not found with username: " + user_name);
    }

    List<GrantedAuthority> authorities = userDTO.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());

    return new org.springframework.security.core.userdetails.User(
            userDTO.getUser_name(),
            userDTO.getUser_password(),
            userDTO.isEnabled(),
            true, true, true,
            //Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
            authorities);
  }

}