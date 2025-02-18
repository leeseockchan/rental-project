package com.road_friends.rentalcar.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class APIReservationDto {
    //예약 번호
    @JsonProperty("reservation_s_id")
    private int reservationSId;

    //예약 차량
    @JsonProperty("car_id")
    private int carId;

    //예약한 유저 번호
    @JsonProperty("user_num")
    private Long userNum;

    //이용 시작날짜
    @JsonProperty("reservation_s_start_date")
    private LocalDateTime reservationSStartDate;

    //이용 종료날짜
    @JsonProperty("reservation_s_end_date")
    private LocalDateTime reservationSEndDate;

    //예약 생성 날짜
    @JsonProperty("reservation_s_create_date")
    private LocalDateTime reservationSCreateDate;

    //쉐어 옵션
    @JsonProperty("share_option")
    private boolean shareOption;

    //예약 상태 ( 0: 예약중 / 1: 이용중 / 2: 이용완료 / 3: 취소 )
    @JsonProperty("reservation_status")
    private int reservationStatus;

    //대여 주차장 번호
    @JsonProperty("rental_station_start")
    private int rentalStationStart;

    //반납 주차장 번호
    @JsonProperty("return_station_end")
    private int returnStationEnd;

}
