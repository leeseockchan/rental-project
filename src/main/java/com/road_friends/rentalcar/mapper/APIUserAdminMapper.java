package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.APIUserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface APIUserAdminMapper {

    // 모든 사용자 조회 (관리자용)
    List<APIUserDto> getAllUsers();

    // 특정 사용자 상세 조회 (관리자용)
    APIUserDto getUserDetail(int userNum);

    // 특정 사용자 정보 수정 (관리자용)
    int updateUser(APIUserDto apiUserDto);
}
