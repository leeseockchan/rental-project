package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.CarDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
  List<CarDto> findAll();
  CarDto findById(Long id);
  void insert(CarDto car);
  void update(CarDto car);
  void delete(Long id);
}
