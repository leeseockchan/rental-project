package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.CarDto;
import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
=======
import java.util.List;

@Mapper
>>>>>>> origin/feat/admin/page-design
public interface CarMapper {
    List<CarDto> findAll();
    CarDto findById(int carId);
    void insert(CarDto carDto);
    void update(CarDto carDto);
    void delete(int carId);
}
