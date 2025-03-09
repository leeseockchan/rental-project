package com.road_friends.rentalcar.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewDTO {
    private Long reviewId;  // 리뷰 ID
    private Long userNum;   // 사용자 ID
    private int reservationId;  // 예약 번호 ID (reservation 테이블)

    private int carConditionSatisfactionRating;  // 차량 상태 만족도
    private int reservationProcessSatisfactionRating;  // 예약 과정 만족도
    private int priceSatisfactionRating;  // 가격 만족도

    private String reviewContent;  // 리뷰 내용
    private LocalDateTime reviewCreatedAt;  // 리뷰 작성 시간
}