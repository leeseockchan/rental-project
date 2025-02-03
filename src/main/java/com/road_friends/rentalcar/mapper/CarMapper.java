package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.CarDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarMapper {
    List<CarDto> findAll();
}
