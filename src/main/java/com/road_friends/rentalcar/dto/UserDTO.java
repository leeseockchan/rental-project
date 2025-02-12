package com.road_friends.rentalcar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Data
public class UserDTO {
  private Long user_num;
  private String user_id;
  private String user_name;
  private String user_password;
  private String user_email;
  private String user_phone;
  private int user_gender;
  private LocalDate user_birth;
  private String user_address;
  private int user_status;
  private boolean enabled = true;
  private List<RoleDTO> roles;
}
