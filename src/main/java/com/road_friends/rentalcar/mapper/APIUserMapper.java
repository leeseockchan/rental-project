package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface APIUserMapper {
  void save(UserDTO userDTO);
  void insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
}
