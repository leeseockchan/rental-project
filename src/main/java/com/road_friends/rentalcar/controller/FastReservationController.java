package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.CarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.service.CarService;
import com.road_friends.rentalcar.service.FastReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class FastReservationController {

    private final FastReservationService fastReservationService;
    private final CarService carService;

    // 차량 목록 조회
    @GetMapping("/vehicles")
    public String getAllCars(Model model){
        List<CarDto> cars = carService.getAllCars();
        model.addAttribute("cars",cars);
        return "car/list";
    }

    // 차량 상세 조회
    @GetMapping("/vehicles/{carId}")
    public String getCar(@PathVariable ("carId") int id,Model model){
        try{
            CarDto car = carService.getCarById(id);
            model.addAttribute("car",car);
        }catch(IllegalStateException e){
            model.addAttribute("message",e.getMessage());
            return "common/error";
        }
        return "car/detail";
    }


    // 차량 예약 화면
    @GetMapping("/reservations")
    public String reserve(Model model){
        List<CarDto> cars = carService.getAllCars();
        model.addAttribute("cars",cars);
        return "car/create";
    }

    // 차량 예약
    @PostMapping("/reservations")
    public void reserveation(@RequestParam("rentalTime") String rentalDateTime,
                             @RequestParam("returnTime") String returnDateTime,
                             FastReservationDto fastReservationDto,
                             Model model){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime rentaldt = LocalDateTime.parse(rentalDateTime, formatter);
        LocalDateTime returndt = LocalDateTime.parse(returnDateTime, formatter);

        fastReservationDto.setRentalDatetime(rentaldt);
        fastReservationDto.setReturnDatetime(returndt);

        fastReservationService.fastReserve(fastReservationDto);

    }

    // 예약 목록
    @GetMapping("/reservationList")
    public String reservations(Model model){
        List<FastReservationDto> reservations = fastReservationService.getReservations();
        model.addAttribute("reservations", reservations);
        return "reservation/list";
    }

    // 예약 상세
    @GetMapping("/reservation/{reservationId}")
    public String reservation(@PathVariable("reservationId") int id, Model model){
        FastReservationDto reservation = fastReservationService.getReservationById(id);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        reservation.setFormattedReservationTime(reservation.getReservationTime().format(formatter));

        CarDto car = carService.getCarById(reservation.getCarId());
        model.addAttribute("reservation",reservation);
        model.addAttribute("car",car);
        return "reservation/detail";
    }

    // 예약 삭제
    @GetMapping("/reservation/{reservationId}/delete")
    public String delete (@PathVariable ("reservationId") int id){
        fastReservationService.deleteReservation(id);
        return "redirect:/api/reservationList";
    }



}
