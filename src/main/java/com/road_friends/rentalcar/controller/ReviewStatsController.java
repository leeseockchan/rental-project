package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class ReviewStatsController {

    private final ReviewService reviewService;

    @GetMapping("/reviewStats")
    public String getReviewStats(Model model) {
        model.addAttribute("reviewCountByDate", reviewService.getReviewCountByDate());
        model.addAttribute("averageSatisfaction", reviewService.getAverageSatisfaction());
        model.addAttribute("reviewStatsByGenderAge", reviewService.getReviewStatsByGenderAge());
        model.addAttribute("reviewStatsByCarModel", reviewService.getReviewStatsByCarModel());
        model.addAttribute("reviewStatsByReservationType", reviewService.getReviewStatsByReservationType());

        return "review/reviewStats"; // Thymeleaf 템플릿명
    }
}

