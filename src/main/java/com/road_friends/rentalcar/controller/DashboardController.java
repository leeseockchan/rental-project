package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.UserStatsDto;
import com.road_friends.rentalcar.service.ReservationService;
import com.road_friends.rentalcar.service.DashboardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardUserService dashboardUserService;
    private final ReservationService reservationService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        // ✅ 사용자 통계 데이터
        UserStatsDto userStats = dashboardUserService.getUserStats();
        model.addAttribute("userStats", userStats);

        // ✅ 가장 인기 있는 차량 (전체)
        model.addAttribute("topAllPopularCars", reservationService.getTopAllPopularCars());

        // ✅ 빠른 예약 시간대 TOP 5
        model.addAttribute("topFastRentalHours", reservationService.getTopFastRentalHours());

        // ✅ 단기 예약 시간대 TOP 5
        model.addAttribute("topShortRentalHours", reservationService.getTopShortRentalHours());

        // ✅ 빠른 예약 - 가장 많이 대여한 지역 TOP 5
        model.addAttribute("topFastRentalLocations", reservationService.getTopFastRentalLocations());

        // ✅ 단기 예약 - 가장 많이 대여한 지역 TOP 5
        model.addAttribute("topShortRentalLocations", reservationService.getTopShortRentalLocations());

        // ✅ 빠른 예약 - 가장 많이 반납한 지역 TOP 5
        model.addAttribute("topFastReturnLocations", reservationService.getTopFastReturnLocations());

        // ✅ 단기 예약 - 가장 많이 반납한 지역 TOP 5
        model.addAttribute("topShortReturnLocations", reservationService.getTopShortReturnLocations());

        // ✅ 빠른 예약 평균 렌트 시간
        model.addAttribute("topFastCarRentalDuration", reservationService.getTopFastCarRentalDuration());
        model.addAttribute("topFastRegionRentalDuration", reservationService.getTopFastRegionRentalDuration());
        model.addAttribute("topFastUserRentalDuration", reservationService.getTopFastUserRentalDuration());

        // ✅ 단기 예약 평균 렌트 시간
        model.addAttribute("topShortCarRentalDuration", reservationService.getTopShortCarRentalDuration());
        model.addAttribute("topShortRegionRentalDuration", reservationService.getTopShortRegionRentalDuration());
        model.addAttribute("topShortUserRentalDuration", reservationService.getTopShortUserRentalDuration());

        // ✅ 빠른 예약 - 가장 인기 있는 차량 TOP 5
        model.addAttribute("topFastPopularCars", reservationService.getTopFastPopularCars());

        // ✅ 단기 예약 - 가장 인기 있는 차량 TOP 5
        model.addAttribute("topShortPopularCars", reservationService.getTopShortPopularCars());

        // ✅ 대시보드 전체 페이지 반환
        return "dashboard/dashboard";
    }
}
