package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.mapper.CarMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
  private final CarMapper carMapper;

  public CarService(CarMapper carMapper) {
    this.carMapper = carMapper;
  }

  public List<CarDto> getAllCars() {
    return carMapper.findAll();
  }

  public CarDto getCarById(Long id) {
    return carMapper.findById(id);
  }

  public void saveCar(CarDto car) {
    carMapper.insert(car);
  }

  public void updateCar(CarDto car) {
    carMapper.update(car);
  }

  public void deleteCar(Long id) {
    carMapper.delete(id);
  }
}
