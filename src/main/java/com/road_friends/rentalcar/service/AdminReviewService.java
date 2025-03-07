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

    // 통계
    public Map<String, Object> getReviewStatistics() {
        Map<String, Object> stats = new HashMap<>();

        int totalResponded = adminReviewMapper.getTotalResponded();
        int totalReservations = adminReviewMapper.getTotalReservations();
        int fastReservations = adminReviewMapper.getFastReservations();
        int shortReservations = adminReviewMapper.getShortReservations();
        int fastResponded = adminReviewMapper.getFastResponded();
        int shortResponded = adminReviewMapper.getShortResponded();

        // 소수점 이하 버림 처리
        int responseRate = (totalReservations > 0) ? (int) ((double) totalResponded / totalReservations * 100) : 0;
        int fastResponseRate = (fastReservations > 0) ? (int) ((double) fastResponded / fastReservations * 100) : 0;
        int shortResponseRate = (shortReservations > 0) ? (int) ((double) shortResponded / shortReservations * 100) : 0;

        stats.put("totalResponded", totalResponded);
        stats.put("responseRate", responseRate);
        stats.put("fastResponseRate", fastResponseRate);
        stats.put("shortResponseRate", shortResponseRate);

        return stats;
    }

    // 차트
    public Map<String, Object> getSatisfactionStatistics() {
        Map<String, Object> chartStats = new HashMap<>();

        List<Map<String, Object>> carConditionStats = adminReviewMapper.getCarConditionSatisfactionStats();
        List<Map<String, Object>> reservationProcessStats = adminReviewMapper.getReservationProcessSatisfactionStats();
        List<Map<String, Object>> priceStats = adminReviewMapper.getPriceSatisfactionStats();

        chartStats.put("carConditionStats", carConditionStats);
        chartStats.put("reservationProcessStats", reservationProcessStats);
        chartStats.put("priceStats", priceStats);

        return chartStats;
    }
}
