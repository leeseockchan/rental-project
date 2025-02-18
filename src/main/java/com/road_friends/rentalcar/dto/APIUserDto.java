package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class APIUserDto {
    private Long userNum;
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private int userGender;
    private LocalDate userBirth;
    private String userAddress;
    private int userStatus;
    private boolean enabled = true;
    // private List<RoleDTO> roles;
}
