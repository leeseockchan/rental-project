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
public class AdminInquiryDto {

    private int inquiryId;

    private int inquiriesNum;

    private int userNum;

    private int adminNum;

    private String inquiriesQ;

    private String inquiriesA;

    private String inquiriesQCreatedAt;

    private String inquiriesACreatedAt;
}
