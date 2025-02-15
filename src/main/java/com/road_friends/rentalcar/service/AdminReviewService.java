package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.mapper.AdminReviewMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminReviewService {

    private final AdminReviewMapper adminReviewMapper;

    public AdminReviewService(AdminReviewMapper adminReviewMapper) {
        this.adminReviewMapper = adminReviewMapper;
    }

    // 전체 리뷰 조회
    public List<ReviewDTO> getAllReviews() {
        return adminReviewMapper.findAllReviews();
    }

    // 특정 리뷰 상세 조회
    public ReviewDTO getReviewById(Long id) {
        return adminReviewMapper.findByReviewId(id);
    }

    // 리뷰 삭제
    public void deleteReview(Long id) {
        adminReviewMapper.deleteReview(id);
    }
}
