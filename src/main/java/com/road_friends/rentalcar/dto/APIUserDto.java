package com.road_friends.rentalcar.dto;

import lombok.Data;

@Data
public class APIUserDto {
    private int userNum;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private int userStatus;
    private int userGender;
    private String userBirth;
}
