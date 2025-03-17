package com.road_friends.rentalcar.controller;


import com.road_friends.rentalcar.dto.LicenseDto;
import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.RentalHistoryDto;
import com.road_friends.rentalcar.dto.UserDto;
import com.road_friends.rentalcar.service.UserAdminService;
import org.springframework.security.core.userdetails.User;
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
    public String getAllUsers(@RequestParam(name="page", defaultValue = "1") int page,
                              @RequestParam(name="size", defaultValue = "10") int size,
                              @RequestParam(value = "name", required = false) String name,
                              Model model) {
        List<UserDto> users;

        // 이름 검색 시
        if (name != null && !name.isEmpty()) {
            users = userAdminService.searchUsersByName(name, page, size); // 이름 검색 결과를 페이지네이션 포함하여 가져오기
        } else {
            // 전체 회원 목록 조회 (페이지네이션 포함)
            users = userAdminService.getAllUsers(page, size);
        }
        // 전체 사용자 수 조회
        int totalUsers = userAdminService.getUserCount(name);
        // PageDto 생성
        PageDto<UserDto> pageDto = new PageDto<>(page, size, totalUsers, users);
        model.addAttribute("pageDto", pageDto);  // 페이지네이션 정보 전달

        // 회원 수 조회
        int userCount = userAdminService.getUserCount();
        model.addAttribute("userCount", userCount);

        // 활성 사용자 수
        int activeUserCount = userAdminService.getActiveUserCount();
        model.addAttribute("activeUserCount", activeUserCount);

        // 탈퇴 사용자 수
        int inactiveUserCount = userAdminService.getInactiveUserCount();
        model.addAttribute("inactiveUserCount", inactiveUserCount);

        // 성별 통계
        int maleUserCount = userAdminService.getMaleUserCount();
        int femaleUserCount = userAdminService.getFemaleUserCount();
        model.addAttribute("maleUserCount", maleUserCount);
        model.addAttribute("femaleUserCount", femaleUserCount);

        // 연령대 통계
        int twentiesCount = userAdminService.getTwentiesCount();
        int thirtiesCount = userAdminService.getThirtiesCount();
        int fortiesCount = userAdminService.getFortiesCount();
        int fiftiesCount = userAdminService.getFiftiesCount();
        int sixtiesCount = userAdminService.getSixtiesCount();

        model.addAttribute("twentiesCount", twentiesCount);
        model.addAttribute("thirtiesCount", thirtiesCount);
        model.addAttribute("fortiesCount", fortiesCount);
        model.addAttribute("fiftiesCount", fiftiesCount);
        model.addAttribute("sixtiesCount", sixtiesCount);

        // 사용자 목록을 모델에 추가
        model.addAttribute("users", users);

        // 검색 후 필드 안에 이름 그대로 남기기
        model.addAttribute("name", name);
        model.addAttribute("userCount", totalUsers);

        return "users/users-list"; // 템플릿으로 반환
    }


    // 특정 사용자 상세 조회
    @GetMapping("/{userNum}")
    public String getUserDetail(@PathVariable("userNum") Long userNum, Model model) {
        UserDto user = userAdminService.getUserDetail(userNum);
        List<RentalHistoryDto> rentalHistory = userAdminService.getUserRentalHistory(userNum); // 대여 기록 조회
        LicenseDto license = userAdminService.getUserLicenseDetail(userNum);
        model.addAttribute("user", user);
        model.addAttribute("rentalHistory", rentalHistory); // 모델에 추가
        model.addAttribute("license", license);
        return "users/users-detail"; // templates/user-detail.html로 이동
    }

    // 특정 사용자 정보 수정
    @PostMapping("/{userNum}")
    public String updateUser(@PathVariable("userNum") Long userNum, @ModelAttribute UserDto userDto) {
        userDto.setUserNum(userNum);
        boolean updated = userAdminService.updateUser(userDto);

        // 수정 성공 시 목록으로 리다이렉트
        return updated ? "redirect:/admin/users" : "error";
    }

}
