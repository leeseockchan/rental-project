package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.APIUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface APIUserMapper {

    // 로그인한 사용자 정보 조회
    APIUserDto findById(String userId);

    // 로그인한 사용자 정보 수정
    void update(APIUserDto apiUserDto);

    // 로그인한 사용자 탈퇴 (enabled 값을 false 설정)
    void disable(String userId);
}
