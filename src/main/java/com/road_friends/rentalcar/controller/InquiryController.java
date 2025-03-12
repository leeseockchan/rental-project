package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.InquiryDto;
import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inquiry")
@CrossOrigin(origins = "http://localhost:3000")
public class InquiryController {

    @Autowired
    InquiryService inquiryService;

    // 고객 질문 전체 조회
    @GetMapping
    public ResponseEntity<PageDto<InquiryDto>> getAllInquiry(@RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        PageDto<InquiryDto> inquiryPage = inquiryService.getAllInquiry(page, size);
        return new ResponseEntity<>(inquiryPage, HttpStatus.OK);
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
        // ID 설정
        inquiryDto.setInquiryId(inquiryId);

        // 업데이트 실행
        boolean updated = inquiryService.updateInquiry(inquiryDto);
        if (updated) {
            return new ResponseEntity<>(inquiryDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 업데이트 실패 시 404 반환
        }
    }

    // 고객 질문 삭제
    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<Void> deleteInquiry(@PathVariable("inquiryId") int inquiryId) {
        inquiryService.deleteInquiry(inquiryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
