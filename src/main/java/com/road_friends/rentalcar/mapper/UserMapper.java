package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
  void save(UserDto userDTO);
  UserDto findByUsername(@Param("userId") String userId);

}
