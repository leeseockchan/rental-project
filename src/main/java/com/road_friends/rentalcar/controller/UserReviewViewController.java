package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.service.UserReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/reviewList")
@CrossOrigin(origins = "http://localhost:3000")
public class UserReviewViewController {

    private final UserReviewService userReviewService;

    public UserReviewViewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    // Thymeleaf 기반 페이징된 리뷰 목록 페이지 반환
    @GetMapping
    public String getUserReviews(@RequestParam int userNum,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {
        PageDto<ReviewDTO> pageDto = userReviewService.getUserReviews(userNum, page, size);
        model.addAttribute("pageDto", pageDto);
        model.addAttribute("userNum", userNum);
        return "reviews/user/reviewListU";
    }
}
