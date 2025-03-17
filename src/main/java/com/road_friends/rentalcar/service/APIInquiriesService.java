package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.APIInquiriesDto;
import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.mapper.APIInquiriesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APIInquiriesService {

    private final APIInquiriesMapper apiInquiriesMapper;

    public APIInquiriesService (APIInquiriesMapper apiInquiriesMapper){
        this.apiInquiriesMapper = apiInquiriesMapper;
    }

    // 로그인한 사용자의 문의사항 전체 조회 (페이징 처리)
    public PageDto<APIInquiriesDto> getUserInquiries(Long userNum, int page, int size) {
        // offset 계산 (예: 1페이지 -> 0, 2페이지 -> 5, ...)
        int offset = (page - 1) * size;

        // 사용자 문의사항 조회
        List<APIInquiriesDto> inquiries = apiInquiriesMapper.getUserInquiries(userNum, offset, size);

        // 전체 문의사항 개수 조회
        int totalElements = apiInquiriesMapper.getUserInquiriesCount(userNum);

        // PageDto로 페이징 정보 포함하여 반환
        return new PageDto<>(page, size, totalElements, inquiries);
    }

    // 로그인한 사용자의 특정 문의사항 상세 조회
    public APIInquiriesDto getUserInquiryDetail(Long inquiryNum){
        return apiInquiriesMapper.getUserInquiryDetail(inquiryNum);
    }
}
