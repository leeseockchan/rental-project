package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  void save(AdminUserDTO adminUserDTO);
  AdminUserDTO findByUsername(String username);
}
