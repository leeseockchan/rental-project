package com.road_friends.rentalcar.dto;

import lombok.*;

@Data
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

    private int inquiriesStatus;

    // 차트용
    private long total;
    private long answered;
    private long unanswered;
    private long activated;
    private long deactivated;

    public AdminInquiryDto(long total, long answered, long unanswered, long activated, long deactivated) {
        this.total = total;
        this.answered = answered;
        this.unanswered = unanswered;
        this.activated = activated;
        this.deactivated = deactivated;
    }
}
