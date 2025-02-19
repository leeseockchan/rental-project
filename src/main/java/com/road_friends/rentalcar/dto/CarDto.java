package com.road_friends.rentalcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
        private int carId;
        private String modelId;
        private int carCategory;
        private int carStatus;
        private int carYear;
        private String carFuel;
        private String carGrade;
        private String carOptions;
        private int rentalStation;


        private String modelBrand;
        private String modelName;
        private String parkingName;

        @Override
        public String toString() {
                return "CarDto{" +
                        "carId=" + carId +
                        ", modelBrand='" + modelBrand + '\'' +
                        ", modelName='" + modelName + '\'' +
                        ", carGrade='" + carGrade + '\'' +
                        ", carCategory=" + carCategory +
                        ", carStatus=" + carStatus +
                        ", parkingName='" + parkingName + '\'' +
                        '}';
        }
}

