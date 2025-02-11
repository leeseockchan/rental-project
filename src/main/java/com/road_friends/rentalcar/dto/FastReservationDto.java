package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FastReservationDto {

    private int reservationId;

    // 위도
    @JsonProperty("current_location_latitude")
    private double latitude;

    // 경도
    @JsonProperty("current_location_longitude")
    private double longitude;

    @JsonProperty("rental_location")
    private String rentalLocation;

    @JsonProperty("rental_datetime")
    private String rentalDatetime;

    @JsonProperty("return_location")
    private String returnLocation;

    @JsonProperty("return_datetime")
    private String returnDatetime;

    @JsonProperty("fast_reservation_create_at")
    private Timestamp reservationTime;
}
