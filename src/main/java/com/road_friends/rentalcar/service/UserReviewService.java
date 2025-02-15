package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.mapper.UserReviewMapper;
import org.springframework.stereotype.Service;
import java.util.List;

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
        reviewMapper.updateReview(reviewDTO);
    }

    public void deleteReview(Long reviewId) {
        reviewMapper.deleteReview(reviewId);
    }

    public ReviewDTO getReviewById(Long reviewId) {
        return reviewMapper.findByReviewId(reviewId);
    }
}