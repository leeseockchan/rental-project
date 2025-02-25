package com.road_friends.rentalcar.service;


import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.APIUserMapper;
import org.springframework.stereotype.Service;

@Service
public class APIUserService {

    private final APIUserMapper apiuserMapper;

    public APIUserService(APIUserMapper apiuserMapper) {
        this.apiuserMapper = apiuserMapper;
    }

    // 로그인한 사용자의 정보 조회
    public UserDTO getUserInfo(String userId) {
        return apiuserMapper.findById(userId);
    }

    // 로그인한 사용자의 정보 수정
    public void updateUserInfo( UserDTO apiuserDTO) {
        apiuserMapper.update(apiuserDTO);
    }

    // 로그인한 사용자의 탈퇴
    public void disableUser(String userId) {
        apiuserMapper.disable(userId);
    }


}
