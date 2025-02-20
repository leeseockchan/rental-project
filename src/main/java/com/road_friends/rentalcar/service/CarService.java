package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.ModelDto;
import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.mapper.CarMapper;
import com.road_friends.rentalcar.mapper.ParkingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;

    //    차량 관리 목록
    public List<CarDto> showCarStatusList() {
        return carMapper.findAllCar();
    }

    //    차량 관리 상세 보기
    public CarDto findByCarId(int carId) {
        return carMapper.findByCarId(carId);
    }

    //      차량 관리 추가
    public void insertCarStatus(CarDto carDto, ParkingDto parkingDto, ModelDto modelDto) {
        carMapper.insertCar(carDto);
    }

    //    차량 관리 수정
    public void modifyCarStatus(CarDto carDto) {
        carMapper.modifyCar(carDto);
    }

    //    차량 상태 삭제
    public void deleteCarStatus(int carId) {
        carMapper.deleteCar(carId);
    }


    //    차량 제조사
    public List<String> carBrandList() {
        return List.of("현대", "제네시스", "기아", "KGM");
    }

    //    차량 이름
    public List<String> modelNameList() {
        return List.of("모닝","k3");
    }

    //    차량 연식
    public List<String> carYearList() {
        return List.of("2020", "2021", "2022", "2023", "2024", "2025");
    }
    //  차량 연료
    public List<String> carFuelList() {
        return List.of("가솔린", "경유", "전기");
    }
    //  차량 등급 리스트
    public List<String> carGradeList() {
        return List.of("Premium", "Standard");
    }
//    주차장 도/시 리스트
    public List<String> parkingProvinceList(){
        return List.of("서울 특별시", "경기도", "충청북도", "충청남도",
                "경상북도", "경상남도", "전라북도", "전라남도", "제주도");
    }
//    주차장 행정 지역 리스트
    public List<String> parkingDistrictList(){
        return List.of("수원시", "평택시", "안양시");
    }


}
