package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.InquiryDto;
import com.road_friends.rentalcar.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryController {

    @Autowired
    InquiryService inquiryService;

    // 고객 질문 전체 조회
    @GetMapping
    public ResponseEntity<List<InquiryDto>> getAllInquiry() {
        List<InquiryDto> inquiry = inquiryService.getAllInquiry();
        return new ResponseEntity<>(inquiry, HttpStatus.OK);
    }

    // 고객 질문 상세 조회
    @GetMapping("/{inquiryId}")
    public ResponseEntity<InquiryDto> getInquiryById(@PathVariable("inquiryId") int inquiryId) {
        InquiryDto inquiry = inquiryService.getInquiryById(inquiryId);
        return inquiry != null ? new ResponseEntity<>(inquiry, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 고객 질문 추가
    @PostMapping
    public ResponseEntity<InquiryDto> addInquiry(@RequestBody InquiryDto inquiryDto) {
        inquiryService.addInquiry(inquiryDto);
        return new ResponseEntity<>(inquiryDto, HttpStatus.CREATED);
    }

    // 고객 질문 수정
     @PostMapping("/{inquiryId}")
     public ResponseEntity<InquiryDto> updateInquiry(@PathVariable("inquiryId") int inquiryId, @RequestBody InquiryDto inquiryDto) {
         inquiryDto.setInquiryId(inquiryId);
         inquiryService.updateInquiry(inquiryDto);
         return new ResponseEntity<>(inquiryDto, HttpStatus.OK);
     }

}
