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
        model.addAttribute("topFastPopularCars", reservationService.getTopFastPopularCars());

        // ⏳ 단기 예약 관련 데이터
        model.addAttribute("topShortPopularCars", reservationService.getTopShortPopularCars());

        // 🔥 가장 인기 있는 차량 TOP 5
        model.addAttribute("topPopularCars", reservationService.getTopPopularCars());

        return "dashboard";
    }

    @GetMapping("/fast-reservation-hours")
    public String fasthour(Model model){
        model.addAttribute("topFastRentalHours", reservationService.getTopFastRentalHours());
        return "hours/fast-reservation-hours";
    }

    @GetMapping("/short-reservation-hours")
    public String shorthour(Model model){
        model.addAttribute("topShortRentalHours", reservationService.getTopShortRentalHours());
        return "hours/short-reservation-hours";
    }

    @GetMapping("/fast-rental-locations")
    public String fastlocations(Model model){
        model.addAttribute("topFastRentalLocations", reservationService.getTopFastRentalLocations());
        return "rental-locations/fast-rental-locations";
    }

    @GetMapping("/short-rental-locations")
    public String shortlocations(Model model){
        //model.addAttribute("topShortRentalLocations", reservationService.getTopShortRentalLocations());
        return "rental-locations/short-rental-locations";
    }

    @GetMapping("/fast-return-locations")
    public String fastreturn(Model model){
        model.addAttribute("topFastReturnLocations", reservationService.getTopFastReturnLocations());
        return "return-locations/fast-return-locations";
    }

    @GetMapping("/short-return-locations")
    public String shortreturn(Model model){
        //model.addAttribute("topShortReturnLocations", reservationService.getTopShortReturnLocations());
        return "return-locations/short-return-locations";
    }

    @GetMapping("/fast-reservation")
    public String fast(Model model){
        model.addAttribute("topFastCarRentalDuration", reservationService.getTopFastCarRentalDuration());
        model.addAttribute("topFastRegionRentalDuration", reservationService.getTopFastRegionRentalDuration());
        model.addAttribute("topFastUserRentalDuration", reservationService.getTopFastUserRentalDuration());
        return "average/fast-reservation";
    }

    @GetMapping("/short-reservation")
    public String reservation(Model model){
        model.addAttribute("topShortCarRentalDuration", reservationService.getTopShortCarRentalDuration());
       // model.addAttribute("topShortRegionRentalDuration", reservationService.getTopShortRegionRentalDuration());
        model.addAttribute("topShortUserRentalDuration", reservationService.getTopShortUserRentalDuration());
        return "average/short-reservation";
    }

}
