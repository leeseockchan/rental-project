package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.service.AdminReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin/review")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    public AdminReviewController(AdminReviewService adminReviewService) {
        this.adminReviewService = adminReviewService;
    }

    // 리뷰 목록 페이지 반환
    @GetMapping
    public String getReviewList(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        // 페이지네이션 처리
        PageDto<ReviewDTO> pageDto = adminReviewService.getAllReviews(page, size);
        model.addAttribute("pageDto", pageDto);

        // 응답률 계산
        Map<String, Object> responseStats = adminReviewService.getReviewResponseStats();
        long totalResponded = (long) responseStats.get("totalResponded");
        int responseRate = (int) responseStats.get("responseRate"); // 응답률을 int로 받기
        model.addAttribute("totalResponded", totalResponded);
        model.addAttribute("responseRate", responseRate);

        return "review/review-list";
    }


    // 특정 리뷰 상세 페이지 반환
    @GetMapping("/{id}")
    public String getReviewById(@PathVariable Long id, Model model) {
        // 리뷰 정보 조회
        ReviewDTO review = adminReviewService.getReviewById(id);
        // 리뷰 작성자의 사용자 정보 조회
        UserDTO user = adminReviewService.getUserById(review.getUserNum());

        // 모델에 추가
        model.addAttribute("review", review);
        model.addAttribute("user", user);

        return "review/review-detail";
    }


    // 리뷰 삭제 후 목록 페이지로 이동
    @PostMapping("/{id}/delete")
    public String deleteReview(@PathVariable Long id) {
        adminReviewService.deleteReview(id);
        return "redirect:/admin/review";
    }
}
