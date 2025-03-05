package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
  void save(UserDTO userDTO);
  UserDTO findByUsername(@Param("userId") String userId);

}
