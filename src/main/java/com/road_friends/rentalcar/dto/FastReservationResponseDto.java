package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FastReservationResponseDto {

    // 사용자 번호
    private Long userNum;
    // 예약 번호
    private int reservationId;
    // 예약 상태
    private Integer rentalState;

    // 대여일시
    @JsonProperty("rental_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rentalDatetime;

    // 대여 위치
    private String rentalLocationName;

    // 대여 상세주소
    private String rentalAddress;

    // 대여 주차장 위도경도
    private BigDecimal rentalLocationLatitude;
    private BigDecimal rentalLocationLongitude;

    // 반납일시
    @JsonProperty("return_datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnDatetime;

    // 반납 위치
    private String returnLocationName;
    private int returnLocation;

    // 반납 상세주소
    private String returnAddress;

    // 반납 주차장 위도경도
    private BigDecimal returnLocationLatitude;
    private BigDecimal returnLocationLongitude;

    // 예약생성일
    private Timestamp reservationTime;

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