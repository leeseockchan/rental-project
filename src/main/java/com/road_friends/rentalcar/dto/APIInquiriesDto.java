package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class APIInquiriesDto {
    private Long inquiriesNum;
    private Long userNum;
    private Long adminNum;
    private String inquiriesQ;
    private String inquiriesA;
    private Timestamp inquiriesQCreatedAt;
    private Timestamp inquiriesACreatedAt;
    private int inquiriesStatus;
}
