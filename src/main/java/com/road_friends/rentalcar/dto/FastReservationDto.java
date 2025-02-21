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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FastReservationDto {

    @JsonProperty("reservation_id")
    private int reservationId;

    // 차량 id
    @JsonProperty("car_id")
    private int carId;

    // 사용자 번호
    @JsonProperty("user_num")
    private Long userNum;

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

    private CarDto carDto;
    private ModelDto modelDto;
    private List<ParkingDto> parkingList;

}
