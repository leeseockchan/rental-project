package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewReservationDTO {
    private int reservationId;
    private int fastReservationId;
    private int shortReservationId;
    private String service;  // "빠른예약" 또는 "단기예약"
    private String modelName;
    private String rentalLocationName;
    private String returnLocationName;
    private LocalDateTime rentalPeriodStart;
    private LocalDateTime rentalPeriodEnd;

    // 포맷된 날짜 필드 추가
    private String rentalPeriodStartFormatted;
    private String rentalPeriodEndFormatted;
}

