package com.road_friends.rentalcar.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIReservationDto {

    //예약한 유저 번호
    private Long userNum;

    //예약 번호
    private int reservationSId;

    // 예약 상태
    private Integer rentalState;

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

    // 대여 위치
    private String rentalLocationName;

    // 대여 위치 상세주소
    private String rentalAddress;

    //예약 생성 날짜
    @JsonProperty("reservation_s_create_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationSCreateDate;

    // 주차장 위도경도
    private BigDecimal rentalLocationLatitude;
    private BigDecimal rentalLocationLongitude;

    private int payment;
    //쉐어 옵션
    private boolean shareOption;

    //대여 주차장 번호
    @JsonProperty("rental_station_start")
    private int rentalStationStart;

    // 차량정보 추가
    // 차량 id
    private int carId;
    private int carYear;
    private String carFuel;
    private String carGrade;
    private String carOptions;
    private CarDto carDto;

    //모델정보
    private String modelName;
    private String modelBrand;  // 제조사
    private String modelCategory;
    private String modelSeateNum;  // 탑승 인원
    private String modelTransmission; // 변속기

    //    private ModelDto modelDto;
    private List<ParkingDto> parkingList;
    private Long totalPrice;

}
