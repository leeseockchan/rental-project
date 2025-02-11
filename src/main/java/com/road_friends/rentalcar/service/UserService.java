package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserMapper userMapper;

  public void signup(UserDTO userDTO) {
    userDTO.setEnabled(true);

    userMapper.save(userDTO);
  }

}
