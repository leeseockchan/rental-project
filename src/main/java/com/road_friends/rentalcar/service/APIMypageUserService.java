package com.road_friends.rentalcar.service;


import com.road_friends.rentalcar.dto.UserDto;
import com.road_friends.rentalcar.mapper.APIMypageUserMapper;
import org.springframework.stereotype.Service;

@Service
public class APIMypageUserService {

    private final APIMypageUserMapper apiuserMapperMypage;

    public APIMypageUserService(APIMypageUserMapper apiuserMapperMypage) {
        this.apiuserMapperMypage = apiuserMapperMypage;
    }

    // 로그인한 사용자의 정보 조회
    public UserDto getUserInfo(String userId) {
        return apiuserMapperMypage.findById(userId);
    }

    // 로그인한 사용자의 정보 수정
    public void updateUserInfo( UserDto apiuserDTO) {
        apiuserMapperMypage.update(apiuserDTO);
    }

    // 로그인한 사용자의 탈퇴
    public void disableUser(String userId) {
        apiuserMapperMypage.disable(userId);
    }


}
