package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private int carId;  // car_id -> carId

    @JsonProperty("car_manufacture")
    @NotBlank(message = "차 제조사는 필수 항목입니다.")
    private String carManufacture;  // car_manufacture -> carManufacture

    @JsonProperty("car_category")
    @NotBlank(message = "차 종류는 필수 항목입니다.")
    private String carCategory;

    @JsonProperty("car_name")
    @NotBlank(message = "차 이름은 필수 항목입니다.")
    private String carName;

    @JsonProperty("car_year")
    @NotBlank(message = "차 연식은 필수 항목입니다.")
    @Pattern(regexp = "^\\d{4}$", message = "차 연식은 4자리 숫자여야 합니다.")  // 4자리 숫자 체크
    private String carYear;

    @JsonProperty("car_fuel")
    @NotBlank(message = "차 연료는 필수 항목입니다.")
    private String carFuel;

    @JsonProperty("car_seate_num")
    @NotNull(message = "차 좌석 수는 필수 항목입니다.")
    private int carSeateNum;

    @JsonProperty("car_transmission")
    @NotBlank(message = "차 변속기는 필수 항목입니다.")
    private String carTransmission;

}
