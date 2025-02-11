package com.road_friends.rentalcar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ReviewDTO {

    private Long id;
    private int userId;
    private String carName;
    private int rating;
    private String title;
    private String content;
    private LocalDate createdDate;
    
}

