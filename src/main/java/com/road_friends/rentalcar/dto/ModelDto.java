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
public class ModelDto {
    private Integer modelId;  // model_id -> modelId

    @JsonProperty("model_manufacture")
    private String modelManufacture;  // model_manufacture -> modelManufacture

    @JsonProperty("model_category")
    private String modelCategory;

    @JsonProperty("model_name")
    private String modelName;

    @JsonProperty("model_year")
    private String modelYear;

    @JsonProperty("model_grade")
    private String modelGrade;

    @JsonProperty("model_fuel")
    private String modelFuel;

    @JsonProperty("model_fuel_efficiency")
    private String modelFuelEfficiency;

    @JsonProperty("model_seate_num")
    private int modelSeateNum;

    @JsonProperty("model_transmission")
    private String modelTransmission;

    @JsonProperty("model_amount_hour")
    private int modelAmountHour;

    @JsonProperty("model_amount_day")
    private int modelAmountDay;

}
