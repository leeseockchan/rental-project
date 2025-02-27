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
    private int reservationId;

    // 차량 id
    @JsonProperty("car_id")
    private int carId;

    // 사용자 번호
    @JsonProperty("user_num")
    private Long userNum;

    // 대여위치
    @JsonProperty("rental_station_start")
    private int rentalLocation;

    // 반납위치 (단기예약은 대여위치와 반납이 같음)
//    @JsonProperty("rental_station_end")
//    private int returnLocation;

    // 대여일시
    @JsonProperty("reservation_s_start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rentalDatetime;

    // 반납일시
    @JsonProperty("reservation_s_end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnDatetime;

    @JsonProperty("reservation_s_create_at")
    private Timestamp reservationTime;

    @JsonProperty("rental_state")
    private Integer rentalState;


    private CarDto carDto;
    private List<ParkingDto> parkingList;

    private Long totalPrice;
}
