package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.dto.ParkingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ParkingMapper {
    List<String> getDistrictsByProvince(@Param("province") String province);
    @Select("SELECT * FROM parking WHERE parking_district = #{district}")
    List<ParkingDto> findByDistrict(String district);
//    주차장 목록 메소드
    List<ParkingDto> findAll();
//    주차장 상세보기
    ParkingDto findByParking(int parkingId);
//    주차장 추가
    void addParking(ParkingDto parkingDto);
//    주차장 수정
    void modifyParking(ParkingDto parkingDto);
//    주차장 삭제
    void deleteParking(int parkingId);


}
