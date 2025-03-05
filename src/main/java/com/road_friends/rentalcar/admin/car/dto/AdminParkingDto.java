package com.road_friends.rentalcar.admin.car.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminParkingDto {
    private int parkingId;

    // 주차장 이름
    private String parkingName;
    // 주차장 주소
    private String parkingAddress;
    // 위도
    private double parkingLatitude;
    // 경도
    private double parkingLongtitude;
    // 지역(도)
    private String parkingProvince;
    // 지역(시)
    private String parkingDistrict;

    private int carCount;

}
