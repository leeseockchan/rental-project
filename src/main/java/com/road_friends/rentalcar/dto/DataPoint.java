package com.road_friends.rentalcar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataPoint {
    private String label;  // 예: "14" (시간대), "서울역", "소나타"
    private int count;     // 해당하는 예약 수

    public DataPoint(String label, int count) {
        this.label = label;
        this.count = count;
    }
}
