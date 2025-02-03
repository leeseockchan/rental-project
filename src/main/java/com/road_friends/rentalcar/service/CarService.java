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

    //모든 차량 조회
    public List<CarDto> getAllCars() {
        return carMapper.findAll();
    }

    // 차량 ID로 조회
    public CarDto getCarById(int carId) {
        return carMapper.findById(carId);
    }

    // 차량 추가
    public void addCar(CarDto carDto) {
        carMapper.insert(carDto);
    }

    // 차량 수정
    public void updateCar(CarDto carDto) {
        carMapper.update(carDto);
    }

    // 차량 삭제
    public void deleteCar(int carId) {
        carMapper.delete(carId);
    }
}
