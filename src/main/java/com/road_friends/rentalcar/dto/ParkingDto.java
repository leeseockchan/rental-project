package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class ParkingDto {

    @JsonProperty("parking_id")
    private Integer parkingId;

    @JsonProperty("parking_name")
    private String parkingName;

    @JsonProperty("parking_address")
    private String parkingAddress;

    @JsonProperty("parking_latitude")
    private BigDecimal parkingLatitude;

    @JsonProperty("parking_longtitude")
    private BigDecimal parkingLongtitude;

    @JsonProperty("parking_province")
    private String parkingProvince;

    @JsonProperty("parking_district")
    private String parkingDistrict;



}
