package com.road_friends.rentalcar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ReviewDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewCountByDate {
        private String reviewDate;
        private int reviewCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AverageSatisfaction {
        private double avgCarCondition;
        private double avgReservationProcess;
        private double avgPrice;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewStatsByGenderAge {
        private String userGender;
        private String ageGroup;
        private int reviewCount;
        private double avgCarCondition;
        private double avgReservationProcess;
        private double avgPrice;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewStatsByCarModel {
        private String modelName;
        private double avgCarCondition;
        private double avgReservationProcess;
        private double avgPrice;
        private int reviewCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewStatsByReservationType {
        private String reservationType;
        private double avgCarCondition;
        private double avgReservationProcess;
        private double avgPrice;
        private int reviewCount;
    }
}
