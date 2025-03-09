package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ReviewShortReservationDTO {
    private int carId;
    private String modelName;
    private int rentalLocation;
    private LocalDateTime rentalDatetime;
    private int returnLocation;
    private LocalDateTime returnDatetime;
    private String rentalLocationName; // 주차장 이름
    private String returnLocationName; // 주차장 이름
    private long userNum;
    private Timestamp fastReservationCreateAt;
    private int rentalState;
    private Integer reservationId;
}