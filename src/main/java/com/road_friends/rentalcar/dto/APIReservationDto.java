package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.util.Date;

@Data
public class APIReservationDto {
    private Long reservationSId;
    private Long carId;
    private Long userNum;
    private Date reservationSStartDate;
    private Date reservationSEndDate;
    private Date reservationSCreateDate;
}
