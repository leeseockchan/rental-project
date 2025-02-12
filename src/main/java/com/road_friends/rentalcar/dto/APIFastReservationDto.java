package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class APIFastReservationDto {
    private Long reservationId;
    private Long userNum;
    private Double currentLocationLatitude;
    private Double currentLocationLongitude;
    private String rentalLocation;
    private LocalDateTime rentalDatetime;
    private String returnLocation;
    private LocalDateTime returnDatetime;
    private LocalDateTime fastReservationCreateAt;
}
