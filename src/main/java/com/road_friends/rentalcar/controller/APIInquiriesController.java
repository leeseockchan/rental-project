package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.component.CustomUserDetails;
import com.road_friends.rentalcar.dto.APIInquiriesDto;
import com.road_friends.rentalcar.service.APIInquiriesService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
public class APIInquiriesController {

   private final APIInquiriesService apiInquiriesService;

   public APIInquiriesController(APIInquiriesService apiInquiriesService){
       this.apiInquiriesService = apiInquiriesService;
   }

   // 로그인한 사용자의 문의사항 전체 조회
   @GetMapping
   public List<APIInquiriesDto> getUserInquiries(@AuthenticationPrincipal CustomUserDetails userDetails) {
     Long userNum = userDetails.getUserNum();  // JWT에서 userNum 가져오기
     return apiInquiriesService.getUserInquiries(userNum);
   }

    // 로그인한 사용자의 특정 문의사항 상세 조회
    @GetMapping("/{inquiriesNum}")
    public APIInquiriesDto getUserInquiryDetail(@PathVariable("inquiriesNum") Long inquiriesNum){
       return apiInquiriesService.getUserInquiryDetail(inquiriesNum);
    }

}
