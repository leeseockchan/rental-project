package com.road_friends.rentalcar.controller;


import com.road_friends.rentalcar.dto.UserDto;
import com.road_friends.rentalcar.service.APIMypageUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class APIMypageUserController {

    private final APIMypageUserService apiMypageUserService;

    public APIMypageUserController(APIMypageUserService apiMypageUserService) {
        this.apiMypageUserService = apiMypageUserService;
    }

    // 로그인한 사용자의 정보 조회
    @GetMapping("/{userId}")
    public UserDto getUserInfo(@PathVariable("userId") String userId) {
        return apiMypageUserService.getUserInfo(userId);
    }

    // 로그인한 사용자의 정보 수정
    @PutMapping("/{userId}")
    public String updateUserInfo(@PathVariable("userId") String userId, @RequestBody UserDto userDto) {
        userDto.setUserId(userId);
        apiMypageUserService.updateUserInfo(userDto);
        return "회원정보가 수정되었습니다.";
    }

    // 로그인한 사용자의 탈퇴 (enabled 값을 false 변경)
    @DeleteMapping("/{userId}")
    public String disableUser(@PathVariable("userId") String userId) {
        apiMypageUserService.disableUser(userId);
        return "회원 탈퇴처리가 완료되었습니다.";
    }



}
