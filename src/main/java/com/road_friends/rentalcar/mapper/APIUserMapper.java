package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.APIUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface APIUserMapper {

    // 로그인한 사용자 정보 조회
    APIUserDto findById(int userId);

    // 로그인한 사용자 정보 수정
    void update(APIUserDto userDto);

    // 로그인한 사용자 탈퇴
    void delete(int userId);

}
