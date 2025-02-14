package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

//     등록된 주차장 목록

    @GetMapping
    public String showAllParking(Model model) {
        List<ParkingDto> parkingList = parkingService.getAllParkings();
        model.addAttribute("parkingList", parkingList);
        return "parking_page/list";
    }


    //    검색한 주차장 조회
    @GetMapping("/{parkingId}")
    public ResponseEntity<ParkingDto> findByParking(@PathVariable("parkingId") int parkingId) {
        ParkingDto parking = parkingService.findByParking(parkingId);
        return parking != null ? new ResponseEntity<>(parking, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //    주차장 추가하기
    @GetMapping("/add")
    public String addParking() {
        return "/parking_page/add";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ParkingDto> addParking(@RequestBody ParkingDto parkingDto) {
        parkingService.addParking(parkingDto);
        return new ResponseEntity<>(parkingDto, HttpStatus.CREATED);
    }

    //    주차장 정보 수정

//    @GetMapping("/modify")
//    public String modifyParking(){
//
//    }
    @PutMapping("/{parkingId}")
    public ResponseEntity<ParkingDto> updateParking(@PathVariable("parkingId") int parkingId,
                                                    @RequestBody ParkingDto parkingDto) {
        parkingDto.setParkingId(parkingId);
        parkingService.updateParking(parkingDto);
        return new ResponseEntity<>(parkingDto, HttpStatus.OK);
    }

    //      차량 삭제
    @DeleteMapping("/parking/{parkingId}/delete")
    public String deleteParking(@PathVariable int parkingId) {
        parkingService.deleteParking(parkingId);
        return "redirect:/parking_page/list";  // 삭제 후 목록 페이지로 리디렉션
    }

}
