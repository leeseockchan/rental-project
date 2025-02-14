package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.service.ReservationService;
import com.road_friends.rentalcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final UserService userService;
    private final ReservationService reservationService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("userStats", userService.getUserStats());
        model.addAttribute("rentalStats", reservationService.getTopRentalLocations());
        model.addAttribute("returnStats", reservationService.getTopReturnLocations());
        model.addAttribute("popularCars", reservationService.getPopularCars());
        return "dashboard";  // `dashboard.html` 렌더링
    }
}
