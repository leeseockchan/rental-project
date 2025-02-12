package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.APIUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APIUserService {

  private final APIUserMapper apiUserMapper;
  private final PasswordEncoder passwordEncoder;

  public void signup(UserDTO userDTO) {
    String encodedPw = passwordEncoder.encode(userDTO.getUser_password());
    userDTO.setUser_password(encodedPw);
    userDTO.setEnabled(userDTO.isEnabled());

    // 사용자 등록
    apiUserMapper.save(userDTO);
    // 권한 등록
    apiUserMapper.insertUserRole(userDTO.getUser_num(), 1);
  }
}
