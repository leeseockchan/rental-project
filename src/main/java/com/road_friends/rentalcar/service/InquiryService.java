package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.InquiryDto;
import com.road_friends.rentalcar.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    InquiryMapper inquiryMapper;

    public List<InquiryDto> getAllInquiry() {
        return inquiryMapper.findAllInquiry();
    }
}
