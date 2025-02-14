package com.road_friends.rentalcar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
