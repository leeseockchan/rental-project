package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.dto.ParkingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParkingMapper {

    List<ParkingDto> findAll();
    ParkingDto findByParking(int parkingId);
    void addParking(ParkingDto parkingDto);
    void updateParking(ParkingDto parkingDto);
    void deleteParking(int parkingId);

}
