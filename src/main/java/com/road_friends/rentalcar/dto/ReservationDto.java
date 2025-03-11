package com.road_friends.rentalcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Integer reservationId;
    private Integer fastReservationId;
    private Integer shortReservationId;

    private String label;  // 예: 지역명, 차량명, 시간대 등
    private int count;     // 예: 대여 횟수, 반납 횟수, 평균 렌트 시간 등
}
