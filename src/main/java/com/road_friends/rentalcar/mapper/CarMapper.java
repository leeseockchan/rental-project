package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.CarDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarMapper {
    List<String> getDistrictsByProvince(@Param("province") String province);
    List<CarDto> findByDistrict(@Param("district") String district);
    List<CarDto> findAllCar();
    CarDto findByCarId(int carId);
    void insertCar(CarDto carDto);
    void modifyCar(CarDto carDto);
    void deleteCar(int carId);
}
