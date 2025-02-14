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
    private String modelId;  // model_id -> modelId

    @JsonProperty("model_brand")
    private String modelManufacture;  // model_manufacture -> modelManufacture

    private String modelName;

    private String modelCategory;

    private String modelSeateNum;

    private String modelTransmission;

    private int modelAmountHour;

    private int modelAmountDay;

}
