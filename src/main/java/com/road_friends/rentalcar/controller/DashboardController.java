package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.UserStatsDto;
import com.road_friends.rentalcar.service.ReservationService;
import com.road_friends.rentalcar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UserService userService;
    private final ReservationService reservationService;

    public DashboardController(UserService userService, ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        // 👥 사용자 통계 데이터
        UserStatsDto userStats = userService.getUserStats();
        model.addAttribute("userStats", userStats);

        // ⏳ 예약 및 렌트 관련 데이터
        model.addAttribute("topRentalHours", reservationService.getTopRentalHours());
        model.addAttribute("topRentalLocations", reservationService.getTopRentalLocations());
        model.addAttribute("topReturnLocations", reservationService.getTopReturnLocations());
        model.addAttribute("popularCars", reservationService.getPopularCars());
        model.addAttribute("averageRentalDurations", reservationService.getAverageRentalDurations());

        return "dashboard";
    }
}
