package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.InquiryDto;
import com.road_friends.rentalcar.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
