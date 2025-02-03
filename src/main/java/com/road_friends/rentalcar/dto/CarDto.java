package com.road_friends.rentalcar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private int carId;  // car_id -> carId
    private String carManufacture;  // car_manufacture -> carManufacture
    private String carCategory;  // car_category -> carCategory
    private String carName;  // car_name -> carName
    private String carYear;  // car_year -> carYear
    private String carFuel;  // car_fuel -> carFuel
    private int carSeateNum;  // car_seate_num -> carSeateNum
    private String carTransmission;  // car_transmission -> carTransmission

}
