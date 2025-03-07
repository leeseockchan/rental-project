package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.*;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdminReviewMapper {

    List<ReviewDTO> getAllReviews(@Param("offset") int offset, @Param("size") int size);

    int getTotalReviewCount();

    ReviewDTO getReviewById(@Param("id") Long id);

    UserDTO getUserById(@Param("userNum") Long userNum);

    void deleteReview(@Param("id") Long id);

    // 예약 정보
    // 1. review_id로 reservation_id를 찾는다.
    int findReservationIdByReviewId(Long reviewId);
    // 2. reservation_id로 예약 정보 조회
    ReservationDTO findReservationById(int reservationId);
    // 3. fast_reservation_id로 fast reservation 정보 조회
    FastReservationDTO findFastReservationById(int fastReservationId);
    // 4. short_reservation_id로 short reservation 정보 조회
    ShortReservationDTO findShortReservationById(int shortReservationId);

    // 통계
    int getTotalResponded();
    int getTotalReservations();
    int getFastReservations();
    int getShortReservations();
    int getFastResponded();
    int getShortResponded();

    // 차트
    List<Map<String, Object>> getCarConditionSatisfactionStats();
    List<Map<String, Object>> getReservationProcessSatisfactionStats();
    List<Map<String, Object>> getPriceSatisfactionStats();
}