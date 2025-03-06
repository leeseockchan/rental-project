package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.dto.ReviewDTO;
import com.road_friends.rentalcar.dto.UserDTO;
import com.road_friends.rentalcar.mapper.AdminReviewMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminReviewService {

    private final AdminReviewMapper adminReviewMapper;

    public AdminReviewService(AdminReviewMapper adminReviewMapper) {
        this.adminReviewMapper = adminReviewMapper;
    }

    public PageDto<ReviewDTO> getAllReviews(int page, int size) {
        int offset = (page - 1) * size;
        List<ReviewDTO> reviews = adminReviewMapper.getAllReviews(offset, size);
        int totalCount = adminReviewMapper.getTotalReviewCount();
        return new PageDto<>(page, size, totalCount, reviews);
    }

    public ReviewDTO getReviewById(Long id) {
        return adminReviewMapper.getReviewById(id);
    }

    public UserDTO getUserById(Long userNum) {
        return adminReviewMapper.getUserById(userNum);
    }

    public void deleteReview(Long id) {
        adminReviewMapper.deleteReview(id);
    }

    // 응답이 있는 리뷰 개수 가져오기
    public long getRespondedReviewCount() {
        return adminReviewMapper.getRespondedReviewCount();
    }

    // 전체 리뷰 개수와 응답률 계산 (응답률을 정수로 처리)
    public Map<String, Object> getReviewResponseStats() {
        // 전체 리뷰 개수
        int totalReviews = adminReviewMapper.getTotalReviewCount();

        // 응답이 있는 리뷰 개수
        long respondedReviews = getRespondedReviewCount();

        // 응답률 계산 (정수로 계산)
        int responseRate = 0;
        if (totalReviews > 0) {
            responseRate = (int) ((respondedReviews * 100) / totalReviews); // 정수로 계산
        }

        // 응답 개수와 응답률 반환
        Map<String, Object> responseStats = new HashMap<>();
        responseStats.put("totalResponded", respondedReviews);
        responseStats.put("responseRate", responseRate);

        return responseStats;
    }
}
