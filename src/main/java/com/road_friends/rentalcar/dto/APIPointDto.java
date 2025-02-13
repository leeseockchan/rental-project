package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class APIPointDto {
    private Long userNum;
    private int changeAmount;
    private String changeType;
    private Timestamp updatedAt;
}
