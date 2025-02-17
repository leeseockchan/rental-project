package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.mapper.ParkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    @Autowired
    private ParkingMapper parkingMapper;

    //     전체 주차장 목록
    public List<ParkingDto> findAll() {
        return parkingMapper.findAll();
    }
    //      특정 주차장 조회
    public ParkingDto findByParking(int parkingId) {
        return parkingMapper.findByParking(parkingId);
    }

    //    주차장 추가하기
    public void addParking(ParkingDto parkingDto) {
        parkingMapper.addParking(parkingDto);
    }

    //    주차장 수정하기
    public void modifyParking(ParkingDto parkingDto) {
        parkingMapper.modifyParking(parkingDto);
    }

    //    주차장 삭제하기
    public void deleteParking(int parkingId) {
        parkingMapper.deleteParking(parkingId);
    }

}
