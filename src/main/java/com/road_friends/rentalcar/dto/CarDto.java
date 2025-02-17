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
    private String modelId;

    @JsonProperty("car_category")
    private int carCategory;

    @JsonProperty("car_status")
    private int carStatus;

    @JsonProperty("car_year")
    private int carYear;

    @JsonProperty("car_fuel")
    private String carFuel;

    @JsonProperty("car_grade")
    private String carGrade;

    @JsonProperty("car_options")
    private String carOptions;

    @JsonProperty("rental_station")
    private int rentalStation;



}
