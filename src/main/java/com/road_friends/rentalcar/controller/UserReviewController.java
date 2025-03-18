package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.service.UserReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class UserReviewController {

    private final UserReviewService userReviewService;

    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    // 특정 유저의 페이징된 리뷰 목록 조회
    @GetMapping
    public ResponseEntity<PageDto<ReviewDTO>> getUserReviews(
            @RequestParam int userNum,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageDto<ReviewDTO> pageDto = userReviewService.getUserReviews(userNum, page, size);
        return ResponseEntity.ok(pageDto);
    }

    // 특정 리뷰 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(userReviewService.getReviewById(id));
    }

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody ReviewDTO reviewDTO) {
        userReviewService.createReview(reviewDTO);
        return ResponseEntity.ok("리뷰 작성 성공!");
    }

    // 리뷰 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setReviewId(id);  // URL에서 받은 ID를 DTO에 설정
        userReviewService.updateReview(reviewDTO);
        return ResponseEntity.ok("리뷰 업데이트 성공!");
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        userReviewService.deleteReview(id);
        return ResponseEntity.ok("리뷰 삭제 성공!");
    }

}
