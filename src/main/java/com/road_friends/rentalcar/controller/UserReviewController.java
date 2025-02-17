package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.service.UserReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/review")
public class UserReviewController {

    private final UserReviewService reviewService;

    public UserReviewController(UserReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.createReview(reviewDTO);
        return ResponseEntity.ok(Map.of("message", "리뷰 작성 성공!"));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Map<String, String>> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setReviewId(reviewId);
        reviewService.updateReview(reviewDTO);
        return ResponseEntity.ok(Map.of("message", "리뷰 업데이트 성공!"));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok(Map.of("message", "리뷰 삭제 성공!"));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }
}
