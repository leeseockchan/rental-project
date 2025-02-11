package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.InquiryDto;
import com.road_friends.rentalcar.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InquiryService {

    @Autowired
    InquiryMapper inquiryMapper;

    public List<InquiryDto> getAllInquiry() {
        return inquiryMapper.findAllInquiry();
    }

    public InquiryDto getInquiryById(int inquiryId) {
        return inquiryMapper.findInquiryById(inquiryId);
    }

    public void addInquiry(InquiryDto inquiryDto) {
        inquiryMapper.insertInquiry(inquiryDto);
    }

    public void updateInquiry(InquiryDto inquiryDto) {
        inquiryMapper.updateInquiry(inquiryDto);
    }

    public void deleteInquiry(int inquiryId) {
        inquiryMapper.deleteInquiry(inquiryId);
    }
}
