package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.service.AdminReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reviews")
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    public AdminReviewController(AdminReviewService adminReviewService) {
        this.adminReviewService = adminReviewService;
    }

    // 페이징된 리뷰 목록 조회
    @GetMapping
    public ResponseEntity<PageDto<ReviewDTO>> getAllReviews(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageDto<ReviewDTO> pageDto = adminReviewService.getAllReviews(page, size);
        return ResponseEntity.ok(pageDto);
    }

    // 특정 리뷰 상세 조회
    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        return adminReviewService.getReviewById(id);
    }

    // 리뷰 삭제 (관리자 권한)
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        adminReviewService.deleteReview(id);
        return "리뷰 삭제 성공!";
    }
}
