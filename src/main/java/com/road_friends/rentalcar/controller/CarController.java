package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.service.APICarService;
import com.road_friends.rentalcar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

  @Autowired
  private CarService carService;

  // 자동차 목록 조회
  @GetMapping
  public String listCars(Model model) {
    model.addAttribute("cars", carService.getAllCars());
    return "car/list"; // Thymeleaf 템플릿 (car/list.html)
  }

  // 자동차 세부 조회
  @GetMapping("/show/{id}")
  public String showCarDetails(@PathVariable Long id, Model model) {
    CarDto car = carService.getCarById(id);
    if (car == null) {
      return "redirect:/cars"; // 차량이 없으면 목록으로 리디렉션
    }
    model.addAttribute("car", car); // "car"로 객체 전달
    return "car/show"; // Thymeleaf 템플릿 (car/show.html)
  }

  // 자동차 추가 폼
  @GetMapping("/new")
  public String showAddForm(Model model) {
    model.addAttribute("car", new CarDto());
    return "car/new"; // Thymeleaf 템플릿 (car/form.html)
  }

  // 자동차 추가 처리
  @PostMapping
  public String addCar(@ModelAttribute CarDto car) {
    carService.saveCar(car);
    return "redirect:/cars";
  }

  // 자동차 수정 폼
  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    model.addAttribute("car", carService.getCarById(id));
    return "car/form"; // Thymeleaf 템플릿 (car/form.html)
  }

  // 자동차 수정 처리
  @PostMapping("/update")
  public String updateCar(@ModelAttribute CarDto car) {
    carService.updateCar(car);
    return "redirect:/cars";
  }

  // 자동차 삭제
  @GetMapping("/delete/{id}")
  public String deleteCar(@PathVariable Long id) {
    carService.deleteCar(id);
    return "redirect:/cars";
  }

}
