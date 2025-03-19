package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.UserDto;
import com.road_friends.rentalcar.service.UserAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    private final UserAdminService userAdminService;

    public UserAdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @GetMapping
    public String getAllUsers(@RequestParam(name = "page", defaultValue = "1") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size,
                              @RequestParam(value = "userId", required = false) String userId,
                              Model model) {

        // 공백 입력 방지
        if (userId != null) {
            userId = userId.trim();
            if (userId.isEmpty()) {
                userId = null;
            }
        }

        List<UserDto> users = (userId != null) ?
                userAdminService.searchUsersById(userId, page, size) : // 아이디 검색
                userAdminService.getAllUsers(page, size); // 전체 목록

        // 전체 사용자 수 조회 (아이디 검색 포함)
        int totalUsers = userAdminService.getUserCountById(userId);

        // PageDto 생성 후 모델에 추가
        model.addAttribute("pageDto", new PageDto<>(page, size, totalUsers, users));
        model.addAttribute("users", users);
        model.addAttribute("userId", userId); // 검색 후 필드 유지

        // 전체 회원 수
        model.addAttribute("userCount", totalUsers);

        // 활성 및 탈퇴 회원 수
        model.addAttribute("activeUserCount", userAdminService.getActiveUserCount());
        model.addAttribute("inactiveUserCount", userAdminService.getInactiveUserCount());

        // 성별 통계
        model.addAttribute("maleUserCount", userAdminService.getMaleUserCount());
        model.addAttribute("femaleUserCount", userAdminService.getFemaleUserCount());

        // 연령대 통계
        model.addAttribute("twentiesCount", userAdminService.getTwentiesCount());
        model.addAttribute("thirtiesCount", userAdminService.getThirtiesCount());
        model.addAttribute("fortiesCount", userAdminService.getFortiesCount());
        model.addAttribute("fiftiesCount", userAdminService.getFiftiesCount());
        model.addAttribute("sixtiesCount", userAdminService.getSixtiesCount());

        return "users/users-list"; // 사용자 목록 페이지 반환
    }

    // 특정 사용자 상세 조회
    @GetMapping("/{userNum}")
    public String getUserDetail(@PathVariable("userNum") Long userNum, Model model) {
        model.addAttribute("user", userAdminService.getUserDetail(userNum));
        return "users/users-detail"; // 상세 페이지 반환
    }

    // 특정 사용자 정보 수정
    @PostMapping("/{userNum}")
    public String updateUser(@PathVariable("userNum") Long userNum, @ModelAttribute UserDto userDto) {
        userDto.setUserNum(userNum);
        return userAdminService.updateUser(userDto) ? "redirect:/admin/users" : "error";
    }
}
