package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminParkingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminParkingMapper {
    List<String> getDistrictsByProvince(@Param("province") String province);
    @Select("SELECT * FROM parking WHERE parking_district = #{district}")
    List<AdminParkingDto> findByDistrict(String district);
//    주차장 목록 메소드
    List<AdminParkingDto> findAll();
//    주차장 상세보기
    AdminParkingDto findByParking(int parkingId);
//    주차장 추가
    void addParking(AdminParkingDto adminParkingDto);
//    주차장 수정
    void modifyParking(AdminParkingDto adminParkingDto);
//    주차장 삭제
    void deleteParking(int parkingId);


}
