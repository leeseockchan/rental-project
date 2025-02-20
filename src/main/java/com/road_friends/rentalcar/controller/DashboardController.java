package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.UserStatsDto;
import com.road_friends.rentalcar.service.ReservationService;
import com.road_friends.rentalcar.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
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

        // ⏳ 빠른 예약 관련 데이터
        model.addAttribute("topFastRentalHours", reservationService.getTopFastRentalHours());
        model.addAttribute("topFastRentalLocations", reservationService.getTopFastRentalLocations());
        model.addAttribute("topFastReturnLocations", reservationService.getTopFastReturnLocations());
        model.addAttribute("topFastPopularCars", reservationService.getTopFastPopularCars());

        // ⏳ 단기 예약 관련 데이터
        model.addAttribute("topShortRentalHours", reservationService.getTopShortRentalHours());
//        model.addAttribute("topShortRentalLocations", reservationService.getTopShortRentalLocations());
//        model.addAttribute("topShortReturnLocations", reservationService.getTopShortReturnLocations());
        model.addAttribute("topShortPopularCars", reservationService.getTopShortPopularCars());

        // 🔥 가장 인기 있는 차량 TOP 5
        model.addAttribute("topPopularCars", reservationService.getTopPopularCars());


        return "dashboard";
    }
    @GetMapping("/fast-reservation")
    public String fast(Model model){
        model.addAttribute("topFastCarRentalDuration", reservationService.getTopFastCarRentalDuration());
        model.addAttribute("topFastRegionRentalDuration", reservationService.getTopFastRegionRentalDuration());
        model.addAttribute("topFastUserRentalDuration", reservationService.getTopFastUserRentalDuration());
        return "reservation/fast-reservation";
    }

    @GetMapping("/short-reservation")
    public String reservation(Model model){
        model.addAttribute("topShortCarRentalDuration", reservationService.getTopShortCarRentalDuration());
//        model.addAttribute("topShortRegionRentalDuration", reservationService.getTopShortRegionRentalDuration());
        model.addAttribute("topShortUserRentalDuration", reservationService.getTopShortUserRentalDuration());
        return "reservation/short-reservation";
    }

}
