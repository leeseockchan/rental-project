package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.service.AdminReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/reviewList")
public class AdminReviewViewController {

    private final AdminReviewService adminReviewService;

    public AdminReviewViewController(AdminReviewService adminReviewService) {
        this.adminReviewService = adminReviewService;
    }

    // Thymeleaf 뷰 반환 (HTML)
    @GetMapping
    public String getReviewList(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        PageDto<ReviewDTO> pageDto = adminReviewService.getAllReviews(page, size);
        model.addAttribute("pageDto", pageDto);
        return "reviews/admin/reviewListA";
    }
}

