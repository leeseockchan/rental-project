package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDto {
  private Long id;
  private String title;
  private String content;
  private Timestamp createdAt;
}