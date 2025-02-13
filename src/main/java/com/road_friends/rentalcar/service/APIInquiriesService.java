package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.APIInquiriesDto;
import com.road_friends.rentalcar.mapper.APIInquiriesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIInquiriesService {

    private final APIInquiriesMapper apiInquiriesMapper;

    public APIInquiriesService (APIInquiriesMapper apiInquiriesMapper){
        this.apiInquiriesMapper = apiInquiriesMapper;
    }

    // 로그인한 사용자의 문의사항 전체 조회
    public List<APIInquiriesDto> getUserInquiries(Long userNum){
        return apiInquiriesMapper.getUserInquiries(userNum);
    }

    // 로그인한 사용자의 특정 문의사항 상세 조회
    public APIInquiriesDto getUserInquiryDetail(Long inquiryNum){
        return apiInquiriesMapper.getUserInquiryDetail(inquiryNum);
    }
}
