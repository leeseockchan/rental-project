package com.road_friends.rentalcar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataPoint {
    private String label;  // 🚀 문자열 (VARCHAR)
    private int count;     // 🚀 정수 (INT)

    public DataPoint(Object label, int count) {
        if (label == null) {
            this.label = "알 수 없음";  // 🔥 NULL 값 방지
        } else if (label instanceof Integer) {
            this.label = label.toString() + "시";  // 숫자는 "10시" 형식으로 변환
        } else {
            this.label = label.toString();  // 🔥 안전한 변환
        }
        this.count = count;
    }
}
