package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/admin/parkings")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    //     등록된 주차장 목록
    @GetMapping
    public String showAllParking(Model model) {
        List<ParkingDto> parkingList = parkingService.findAll();
        model.addAttribute("parkingList", parkingList);
        return "parking_page/list";
    }


    //    검색한 주차장 조회
//    @GetMapping("/{parkingId}")
//    public ResponseEntity<ParkingDto> findByParking(@PathVariable("parkingId") int parkingId) {
//        ParkingDto parking = parkingService.findByParking(parkingId);
//       return parking != null ? new ResponseEntity<>(parking, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    주차장 상세 보기
    @GetMapping("/{parkingId}")
    public String detailByParking(@PathVariable int parkingId, Model model) {
        ParkingDto parkingDetail = parkingService.findByParking(parkingId);
        model.addAttribute("parkingDetail", parkingDetail);
        return "parking_page/detail";
    }

    //    주차장 추가하기
    @GetMapping("/add")
    public String addParking() {
        return "parking_page/add";
    }
    @PostMapping
    public String add(@ModelAttribute ParkingDto parkingDto){
        parkingService.addParking(parkingDto);
        return "redirect:/api/admin/parkings";
    }

    //    주차장 정보 수정
    @GetMapping("/{parkingId}/modify")
    public String modify(@PathVariable int parkingId, Model model) {
        ParkingDto parkingDto = parkingService.findByParking(parkingId);
        model.addAttribute("modify", parkingDto);
        return "parking_page/modify";
    }
    @PutMapping("/{parkingId}/modify")
    public String modifyParking(@PathVariable int parkingId,
                                @ModelAttribute ParkingDto parkingDto,
                                RedirectAttributes redirectAttributes) {
        parkingDto.setParkingId(parkingId);
        parkingService.modifyParking(parkingDto);
        return "redirect:/api/admin/parkings/" + parkingId;
    }

    //      주차장 삭제
    @DeleteMapping("/{parkingId}")
    public String deleteParking(@PathVariable int parkingId) {
        parkingService.deleteParking(parkingId);
        return "redirect:/api/admin/parkings";  // 삭제 후 목록 페이지로 리디렉션
    }

}
