package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.service.UserReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class UserReviewController {

    private final UserReviewService reviewService;

    public UserReviewController(UserReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 리뷰 작성
    @PostMapping
    public String createReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.createReview(reviewDTO);
        return "리뷰 작성 성공!";
    }

    // 리뷰 수정
    @PutMapping("/{id}")
    public String updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setId(id);
        reviewService.updateReview(reviewDTO);
        return "리뷰 업데이트 성공!";
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "리뷰 삭제 성공!";
    }

    // 리뷰 조회
    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }
}
