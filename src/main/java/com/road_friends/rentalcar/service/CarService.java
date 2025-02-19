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
//    차량 상태 삭제
    public void deleteCarStatus(int carId){
        carMapper.deleteCar(carId);
    }


//  차량 등급 리스트
    public List<String> carGradeList(){
        return List.of("Premium", "Standard");
    }
//  차량 대여카테고리
    public List<String> carCategoryList(){
        return List.of("빠른대여", "일반대여");
    }
//    차량 현황
    public List<String> carStatusList(){
        return List.of("대여중","수리중","대여가능");
    }
    //  차량 연료
    public List<String> carFuelList(){
        return List.of("가솔린","경유","전기");
    }
//    차량 연식
    public List<String> carYearList(){
        return List.of("2020","2021","2022", "2023","2024","2025");
    }

//    차량 제조사
    public List<String> carBrandList(){
        return List.of("현대","제네시스","기아","KGM");
    }

}
