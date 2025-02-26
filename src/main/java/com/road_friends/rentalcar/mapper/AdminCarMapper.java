package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminCarDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminCarMapper {
    List<String> getDistrictsByProvince(@Param("province") String province);
    List<AdminCarDto> findByDistrict(@Param("district") String district);
    List<AdminCarDto> findAllCar();
    AdminCarDto findByCarId(int carId);
    void insertCar(AdminCarDto adminCarDto);
    void modifyCar(AdminCarDto adminCarDto);
    void deleteCar(int carId);
}
