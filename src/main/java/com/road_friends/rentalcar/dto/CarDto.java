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
public class CarDto {
    private int carId;  // car_id -> carId

    @JsonProperty("model_id")
    private int modelId;

    @JsonProperty("car_manufacture")
    private String carManufacture;  // car_manufacture -> carManufacture

    @JsonProperty("car_category")
    private int carCategory;

    @JsonProperty("car_status")
    private int carStatus;

    @JsonProperty("car_name")
    private String carName;

    @JsonProperty("car_year")
    private String carYear;

    @JsonProperty("car_fuel")
    private String carFuel;

    @JsonProperty("car_seate_num")
    private int carSeateNum;

    @JsonProperty("car_transmission")
    private String carTransmission;



}
