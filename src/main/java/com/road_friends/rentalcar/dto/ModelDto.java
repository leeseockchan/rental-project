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

    @JsonProperty("model_name")
    private String modelName;

    @JsonProperty("model_category")
    private String modelCategory;

    @JsonProperty("model_seate_num")
    private String modelSeateNum;

    @JsonProperty("model_transmission")
    private String modelTransmission;

    @JsonProperty("model_amount_hour")
    private int modelAmountHour;

    @JsonProperty("model_amount_day")
    private int modelAmountDay;

}
