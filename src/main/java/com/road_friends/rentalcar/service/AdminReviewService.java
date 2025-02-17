package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.mapper.AdminReviewMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminReviewService {

    private final AdminReviewMapper adminReviewMapper;

    public AdminReviewService(AdminReviewMapper adminReviewMapper) {
        this.adminReviewMapper = adminReviewMapper;
    }

    public List<ReviewDTO> getAllReviews() {
        return adminReviewMapper.findAllReviews();
    }

    public ReviewDTO getReviewById(Long id) {
        return Optional.ofNullable(adminReviewMapper.findByReviewId(id))
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
    }

    public void deleteReview(Long id) {
        adminReviewMapper.deleteReview(id);
    }
}
