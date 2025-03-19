package com.road_friends.rentalcar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class UserDto {
    @JsonProperty("user_num")
    private Long userNum;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_password")
    private String userPassword;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("user_phone")
    private String userPhone;

    @JsonProperty("user_gender")
    private int userGender;

    @JsonProperty("user_birth")
    private LocalDate userBirth;

    @JsonProperty("user_address")
    private String userAddress;

    @JsonProperty("user_status")
    private int userStatus;

    private boolean enabled = true;

//    private List<RoleDTO> roles;
}
