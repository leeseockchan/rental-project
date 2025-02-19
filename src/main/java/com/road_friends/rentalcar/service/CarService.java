package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;

//    차량 관리 목록
    public List<CarDto> showCarStatusList(){
        return carMapper.findAllCar();
    }
//    차량 관리 상세 보기
    public CarDto findByCarId(int carId){
        return carMapper.findByCarId(carId);
    }
//      차량 관리 추가
    public void insertCarStatus(CarDto carDto){
        carMapper.insertCar(carDto);
    }
//    차량 관리 수정
    public void modifyCarStatus(CarDto carDto){
        carMapper.modifyCar(carDto);
    }
//    차량 관리 차량 ID 찾기
    public CarDto findById(int carId){
        return carMapper.findByCarId(carId);
    }
//    차량 상태 삭제
    public void deleteCarStatus(int carId){
        carMapper.deleteCar(carId);
    }
}
