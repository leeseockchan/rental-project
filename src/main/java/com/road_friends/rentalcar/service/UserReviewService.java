package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.mapper.UserReviewMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserReviewService {
    private final UserReviewMapper reviewMapper;

    public UserReviewService(UserReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public void createReview(ReviewDTO reviewDTO) {
        reviewMapper.insertReview(reviewDTO);
    }

    public void updateReview(ReviewDTO reviewDTO) {
        if (reviewDTO.getReviewId() == null) {
            throw new IllegalArgumentException("리뷰 ID가 필요합니다.");
        }
        reviewMapper.updateReview(reviewDTO);
    }

    public void deleteReview(Long reviewId) {
        reviewMapper.deleteReview(reviewId);
    }

    public ReviewDTO getReviewById(Long reviewId) {
        return Optional.ofNullable(reviewMapper.findByReviewId(reviewId))
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
    }
}
