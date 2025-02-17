package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FastReservationDto {


    private int reservationId;

    // 차량 id
    @JsonProperty("car_id")
    private int carId;

    // 사용자 번호
    @JsonProperty("user_num")
    private int userNum;

    // 대여위치
    @JsonProperty("rental_location")
    private int rentalLocation;

    // 대여일시
    @JsonProperty("rental_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rentalDatetime;

    // 반납위치
    @JsonProperty("return_location")
    private int returnLocation;

    // 반납일시
    @JsonProperty("return_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnDatetime;

    @JsonProperty("fast_reservation_create_at")
    private Timestamp reservationTime;

}
