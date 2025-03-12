package com.road_friends.rentalcar.controller;


import com.road_friends.rentalcar.component.CustomUserDetails;
import com.road_friends.rentalcar.dto.UserDto;
import com.road_friends.rentalcar.service.APIMypageUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class APIMypageUserController {

    private final APIMypageUserService apiMypageUserService;

    public APIMypageUserController(APIMypageUserService apiMypageUserService) {
        this.apiMypageUserService = apiMypageUserService;
    }

    // 로그인한 사용자의 정보 조회
    @GetMapping("/mypage")
    public UserDto getUserInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // 현재 인증된 사용자의 userId 추출
//        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        String userId = userDetails.getUsername();
        return apiMypageUserService.getUserInfo(userId);
    }

    // 로그인한 사용자의 정보 수정
    @PutMapping("/mypage")
    public String updateUserInfo(@RequestBody UserDto userDto) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        userDto.setUserId(userId);
        apiMypageUserService.updateUserInfo(userDto);
        return "회원정보가 수정되었습니다.";
    }

    // 로그인한 사용자의 탈퇴
    @DeleteMapping("/mypage")
    public String disableUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        apiMypageUserService.disableUser(userId);
        return "회원 탈퇴처리가 완료되었습니다.";
    }
}