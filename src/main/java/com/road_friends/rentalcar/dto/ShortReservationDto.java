package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortReservationDto {

    @JsonProperty("reservation_s_id")
    private int reservationSId;

    // 차량 id
    @JsonProperty("car_id")
    private int carId;

    // 사용자 번호
    @JsonProperty("user_num")
    private Long userNum;

    // 대여위치
    @JsonProperty("rental_station_start")
    private int rentalStationStart;

    // 대여일시
    @JsonProperty("reservation_s_start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationSStartDate;

    // 반납일시
    @JsonProperty("reservation_s_end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationSEndDate;

    @JsonProperty("reservation_s_create_at")
    private Timestamp reservationSCreateAt;

    @JsonProperty("rental_state")
    private Integer rentalState;

    private CarDto carDto;

    @JsonProperty("payment")
    private Long totalPrice;
}