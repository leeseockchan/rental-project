package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReservationDTO;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.service.AdminReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
                                @RequestParam(required = false) Integer year,
                                @RequestParam(required = false) Integer month,
                                Model model) {

        PageDto<ReviewDTO> pageDto;

        if (year != null || month != null) {
            pageDto = adminReviewService.getReviewsByDate(page, size, year, month);
        } else {
            pageDto = adminReviewService.getAllReviews(page, size);
        }

        // null 방지: pageDto가 null이면 빈 리스트를 할당
        if (pageDto == null) {
            pageDto = new PageDto<>(page, size, 0, Collections.emptyList());
        }
        model.addAttribute("pageDto", pageDto);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedMonth", month);

        Map<String, Object> stats = adminReviewService.getReviewStatistics();
        model.addAttribute("totalResponded", stats.get("totalResponded"));
        model.addAttribute("responseRate", stats.get("responseRate"));
        model.addAttribute("fastResponseRate", stats.get("fastResponseRate"));
        model.addAttribute("shortResponseRate", stats.get("shortResponseRate"));

        Map<String, Object> chartStats = adminReviewService.getSatisfactionStatistics();
        model.addAttribute("carConditionStats", chartStats.get("carConditionStats"));
        model.addAttribute("reservationProcessStats", chartStats.get("reservationProcessStats"));
        model.addAttribute("priceStats", chartStats.get("priceStats"));

        return "review/review-list";
    }


    // 특정 리뷰 상세 페이지 반환
    @GetMapping("/{id}")
    public String getReviewById(@PathVariable Long id, Model model) {
        // 리뷰 정보 조회
        ReviewDTO review = adminReviewService.getReviewById(id);
        // 리뷰 작성자의 사용자 정보 조회
        UserDTO user = adminReviewService.getUserById(review.getUserNum());

        // 리뷰 ID를 통해 예약 정보를 찾는다
        ReservationDTO reservationDTO = adminReviewService.getReservationDetailsByReviewId(id);
        // 예약 정보를 모델에 추가
        model.addAttribute("reservation", reservationDTO);

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
