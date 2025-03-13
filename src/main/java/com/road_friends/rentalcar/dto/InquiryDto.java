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
public class InquiryDto {

    private int inquiryId;

    @JsonProperty("inquiries_num")
    private int inquiriesNum;

    @JsonProperty("user_num")
    private int userNum;

    @JsonProperty("admin_num")
    private int adminNum;

    @JsonProperty("inquiries_q")
    private String inquiriesQ;

    @JsonProperty("inquiries_a")
    private String inquiriesA;

    @JsonProperty("inquiries_q_created_at")
    private String inquiriesQCreatedAt;

    @JsonProperty("inquiries_a_created_at")
    private String inquiriesACreatedAt;
}
