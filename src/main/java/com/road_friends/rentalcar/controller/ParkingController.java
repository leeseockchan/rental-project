package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.ParkingDto;
import com.road_friends.rentalcar.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin/parkings")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

//     등록된 주차장 목록
    @GetMapping
    public ResponseEntity<List<ParkingDto>> getAllParkings() {
        List<ParkingDto> parkingList = parkingService.getAllParkings();
        return new ResponseEntity<>(parkingList, HttpStatus.OK);
    }

//    검색한 주차장 조회
    @GetMapping("/{parkingId}")
    public ResponseEntity<ParkingDto> findByParking(@PathVariable("parkingId") int parkingId) {
        ParkingDto Parking = parkingService.findByParking(parkingId);
        return Parking != null ?  new ResponseEntity<>(Parking,  HttpStatus.OK)
                : new ResponseEntity<>(Parking, HttpStatus.NOT_FOUND);
    }

//    주차장 추가하기
    @PostMapping
    public ResponseEntity<ParkingDto> addParking(ParkingDto parkingDto){
        parkingService.addParking(parkingDto);
        return new ResponseEntity<>(parkingDto, HttpStatus.CREATED);
    }
    
//    주차장 정보 수정
    @PutMapping("/{parkingId}")
    public ResponseEntity<ParkingDto> updateParking(@PathVariable("parkingId") int parkingId,
                                                    ParkingDto parkingDto){
        parkingDto.setParkingId(parkingId);
        parkingService.updateParking(parkingDto);
        return new ResponseEntity<>(parkingDto, HttpStatus.OK);
    }

//      차량 삭제
    @DeleteMapping("/{parkingId}")
    public ResponseEntity<Void> deletemodel(@PathVariable("parkingId") int parkingId) {
        parkingService.deleteParking(parkingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
