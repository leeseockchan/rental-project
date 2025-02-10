package com.road_friends.rentalcar.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDTO {

    private int user_id;
    private String car_name;
    private int rating;
    private String title;
    private String content;
    private LocalDate created_date;
}
