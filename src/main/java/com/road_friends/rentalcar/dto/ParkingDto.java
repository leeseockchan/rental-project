package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {
    private Integer parkingId;

    @JsonProperty("parking_name")       // 주차장 이름
    private String parkingName;

    @JsonProperty("parking_address")    // 주차장 주소
    private String parkingAddress;

    @JsonProperty("parking_latitude")   // 위도
    private double  parkingLatitude;

    @JsonProperty("parking_longitude")  // 경도
    private double parkingLongitude;

    @JsonProperty("parking_type")       // 주차장 유형
    private String parkingType;

}
