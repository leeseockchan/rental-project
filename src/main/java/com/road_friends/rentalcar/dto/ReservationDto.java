package com.road_friends.rentalcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationDto {
    private String label;  // 지역명, 차량명 등
    private Long count;    // 예약 횟수
}
