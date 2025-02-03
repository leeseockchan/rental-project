package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.CarDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CarMapper {
    List<CarDto> findAll();
    CarDto findById(int carId);
    void insert(CarDto carDto);
    void update(CarDto carDto);
    void delete(int carId);
}
