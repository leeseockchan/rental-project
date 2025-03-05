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

  @JsonProperty("car_category")
  private String carCategory;


  @JsonProperty("car_year")

  @JsonProperty("car_fuel")
  private String carFuel;



