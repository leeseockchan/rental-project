package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.service.DashboardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class DashboardUserController {
    private final DashboardUserService dashboardUserService;

    // 전체 회원 통계 (회원 수, 연령대, 성별 통계)
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        return ResponseEntity.ok(dashboardUserService.getUserStatsAsMap());
    }

    // 연령대 및 성별 통계
    @GetMapping("/age-gender")
    public ResponseEntity<Map<String, Object>> getUserAgeGenderStats() {
        return ResponseEntity.ok(dashboardUserService.getUserStatsAsMap());
    }
}
