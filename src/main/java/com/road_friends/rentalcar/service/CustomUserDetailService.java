package com.road_friends.rentalcar.service;

import java.util.List;
import java.util.stream.Collectors;

import com.road_friends.rentalcar.component.CustomUserDetails;
import com.road_friends.rentalcar.dto.RoleDto;
import com.road_friends.rentalcar.dto.UserDto;
import com.road_friends.rentalcar.mapper.UserMapper;
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
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    UserDto userDTO = userMapper.findByUsername(userId);
    if (userDTO == null) {
      throw new UsernameNotFoundException("User not found with username: " + userId);
    }

    // 탈퇴 여부 확인
    if (!userDTO.isEnabled() || userDTO.getUserStatus() == 2) {
      throw new RuntimeException("This account has been deactivated.");
    }

    // 권한 정보 생성
    List<String> roles = UserDto.getRoles().stream()
            .map(RoleDto::getName) // RoleDto의 이름을 가져오는 방법은 필요에 맞게 수정
            .collect(Collectors.toList());

    // CustomUserDetails 객체 생성
    return new CustomUserDetails(
            userDTO.getUserNum(),
            userDTO.getUserId(),
            userDTO.getUserPassword(),
            userDTO.isEnabled(),
            userDTO.getUserStatus(),
            roles
    );
  }
}
