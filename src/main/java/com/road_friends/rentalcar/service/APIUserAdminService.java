package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.APIUserDto;
import com.road_friends.rentalcar.mapper.APIUserAdminMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIUserAdminService {

    private final APIUserAdminMapper apiUserAdminMapper;

    public APIUserAdminService(APIUserAdminMapper apiUserAdminMapper) {
        this.apiUserAdminMapper = apiUserAdminMapper;
    }


    // 모든 사용자 조회 (관리자용)
    public List<APIUserDto> getAllUsers() {
        return apiUserAdminMapper.getAllUsers();
    }

    // 특정 사용자 상세 조회 (관리자용)
    public APIUserDto getUserDetail(Long userNum) {
        return apiUserAdminMapper.getUserDetail(userNum);
    }

    // 특정 사용자 정보 수정 (관리자용)
    public boolean updateUser(APIUserDto apiUserDto) {
        return apiUserAdminMapper.updateUser(apiUserDto) > 0;
    }
}