package com.road_friends.rentalcar.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminCarDto {
        private int carId;
        private int carCategory;
        private int carStatus;
        private int carYear;
        private String carFuel;
        private String carGrade;
        private String carOptions;

        private AdminModelDto model;   // 🔹 ModelDto 객체 포함
        private AdminParkingDto parking;  // 🔹 ParkingDto 객체 포함

        @Override
        public String toString() {
                return "CarDto{" +
                        "carId=" + carId +
                        ", carGrade='" + carGrade + '\'' +
                        ", carCategory=" + carCategory +
                        ", carStatus=" + carStatus +
                        ", carYear=" + carYear +
                        ", carFuel='" + carFuel + '\'' +
                        ", model=" + model +   // ModelDto 전체 출력
                        ", parking=" + parking + // ParkingDto 전체 출력
                        '}';
        }

}

