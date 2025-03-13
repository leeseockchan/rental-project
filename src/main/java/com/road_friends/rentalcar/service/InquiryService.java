package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.InquiryDto;
import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    InquiryMapper inquiryMapper;

    public PageDto<InquiryDto> getAllInquiry(int page, int size) {
        int totalElements = inquiryMapper.getTotalInquiries();
        int offset = (page - 1) * size;
        List<InquiryDto> inquiryDtos = inquiryMapper.getPagedInquiries(offset, size);
        PageDto<InquiryDto> pageDto = new PageDto<>(page, size, totalElements, inquiryDtos);
        return pageDto;
    }

    public InquiryDto getInquiryById(int inquiryId) {
        return inquiryMapper.findInquiryById(inquiryId);
    }

    public void addInquiry(InquiryDto inquiryDto) {
        inquiryMapper.insertInquiry(inquiryDto);
    }

    public boolean updateInquiry(InquiryDto inquiryDto) {
        int rowsAffected = inquiryMapper.updateInquiry(inquiryDto);
        return rowsAffected > 0; // 업데이트 성공 여부 반환
    }

    public void deleteInquiry(int inquiryId) {
        inquiryMapper.deleteInquiry(inquiryId);
    }
}
