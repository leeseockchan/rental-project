package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.AdminParkingDto;
import com.road_friends.rentalcar.mapper.AdminParkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminParkingService {
    @Autowired
    private AdminParkingMapper adminParkingMapper;

    public List<String> getDistrictsByProvince(String province) {
        return adminParkingMapper.getDistrictsByProvince(province);
    }

    public List<AdminParkingDto> findByDistrict(String district) {
        return adminParkingMapper.findByDistrict(district);
    }

    //     전체 주차장 목록
    public List<AdminParkingDto> findAll() {
        return adminParkingMapper.findAll();
    }
    //      특정 주차장 조회
    public AdminParkingDto findByParking(int parkingId) {
        return adminParkingMapper.findByParking(parkingId);
    }

    //    주차장 추가하기
    public void addParking(AdminParkingDto adminParkingDto) {
        adminParkingMapper.addParking(adminParkingDto);
    }

    //    주차장 수정하기
    public void modifyParking(AdminParkingDto adminParkingDto) {
        adminParkingMapper.modifyParking(adminParkingDto);
    }

    //    주차장 삭제하기
    public void deleteParking(int parkingId) {
        adminParkingMapper.deleteParking(parkingId);
    }


}
