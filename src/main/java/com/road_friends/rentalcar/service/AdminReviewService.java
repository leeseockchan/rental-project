package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.PageDto;
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

    // 페이징된 리뷰 목록 조회
    public PageDto<ReviewDTO> getAllReviews(int page, int size) {
        int totalElements = adminReviewMapper.countReviews();
        int offset = (page - 1) * size;

        List<ReviewDTO> reviews = adminReviewMapper.findAllReviews(size, offset);

        return new PageDto<>(page, size, totalElements, reviews);
    }

    public ReviewDTO getReviewById(Long id) {
        return Optional.ofNullable(adminReviewMapper.findByReviewId(id))
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다."));
    }

    public void deleteReview(Long id) {
        adminReviewMapper.deleteReview(id);
    }
}
