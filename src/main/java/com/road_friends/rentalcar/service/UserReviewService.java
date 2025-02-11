package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.mapper.UserReviewMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserReviewService {

    private final UserReviewMapper reviewMapper;

    public UserReviewService(UserReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    // 리뷰 작성
    public void createReview(ReviewDTO reviewDTO) {
        reviewDTO.setCreatedDate(LocalDate.now());
        reviewMapper.insertReview(reviewDTO);
    }

    // 리뷰 수정
    public void updateReview(ReviewDTO reviewDTO) {
        reviewMapper.updateReview(reviewDTO);
    }

    // 리뷰 삭제
    public void deleteReview(Long id) {
        reviewMapper.deleteReview(id);
    }

    // 리뷰 조회
    public ReviewDTO getReviewById(Long id) {
        return reviewMapper.findByReviewId(id);
    }
}
