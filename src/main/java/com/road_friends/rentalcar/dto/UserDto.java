package com.road_friends.rentalcar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class UserDto {
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
  private List<RoleDto> roles;
}
