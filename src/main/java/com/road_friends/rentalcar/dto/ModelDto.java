package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ModelDto {

    @JsonProperty("model_id")
    private int ModelId;

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
