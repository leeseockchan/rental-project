package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.*;
import com.road_friends.rentalcar.mapper.AdminReviewMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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

    // 예약 정보
    public ReviewReservationDTO getReservationDetailsByReviewId(Long reviewId) {
        // 1. review_id를 사용하여 해당 review의 reservation_id를 찾는다
        int reservationId = adminReviewMapper.findReservationIdByReviewId(reviewId);

        // 2. 예약 정보 조회
        ReviewReservationDTO reviewReservationDTO = adminReviewMapper.findReservationById(reservationId);

        // 3. 예약의 fast_reservation_id 또는 short_reservation_id에 따라 이용 서비스를 결정한다.
        if (reviewReservationDTO.getFastReservationId() != 0) {
            reviewReservationDTO.setService("빠른예약");
            // 4. fast_reservation_id가 존재한다면, fast_reservation 테이블에서 필요한 값들을 가져온다.
            ReviewFastReservationDTO reviewFastReservationDTO = adminReviewMapper.findFastReservationById(reviewReservationDTO.getFastReservationId());
            reviewReservationDTO.setRentalLocationName(reviewFastReservationDTO.getRentalLocationName());
            reviewReservationDTO.setReturnLocationName(reviewFastReservationDTO.getReturnLocationName());
            reviewReservationDTO.setRentalPeriodStart(reviewFastReservationDTO.getRentalDatetime());
            reviewReservationDTO.setRentalPeriodEnd(reviewFastReservationDTO.getReturnDatetime());
        } else if (reviewReservationDTO.getShortReservationId() != 0) {
            reviewReservationDTO.setService("단기예약");
            // 5. short_reservation_id가 존재한다면, short_reservation 테이블에서 필요한 값들을 가져온다.
            ReviewShortReservationDTO reviewShortReservationDTO = adminReviewMapper.findShortReservationById(reviewReservationDTO.getShortReservationId());
            reviewReservationDTO.setRentalLocationName(reviewShortReservationDTO.getRentalLocationName());
            reviewReservationDTO.setReturnLocationName(reviewShortReservationDTO.getReturnLocationName());
            reviewReservationDTO.setRentalPeriodStart(reviewShortReservationDTO.getRentalDatetime());
            reviewReservationDTO.setRentalPeriodEnd(reviewShortReservationDTO.getReturnDatetime());
        }

        // 날짜 포맷 처리
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (reviewReservationDTO.getRentalPeriodStart() != null) {
            reviewReservationDTO.setRentalPeriodStartFormatted(reviewReservationDTO.getRentalPeriodStart().format(formatter));
        }
        if (reviewReservationDTO.getRentalPeriodEnd() != null) {
            reviewReservationDTO.setRentalPeriodEndFormatted(reviewReservationDTO.getRentalPeriodEnd().format(formatter));
        }

        return reviewReservationDTO;
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

    // 필터
    public PageDto<ReviewDTO> getReviewsByDate(int page, int size, Integer year, Integer month) {
        int totalElements = adminReviewMapper.countReviewsByDate(year, month);
        int offset = (page - 1) * size;
        List<ReviewDTO> items = adminReviewMapper.findReviewsByDate(year, month, size, offset);
        return new PageDto<>(page, size, totalElements, items);
    }
}
