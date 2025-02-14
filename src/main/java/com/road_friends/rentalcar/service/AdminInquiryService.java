package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import com.road_friends.rentalcar.mapper.AdminInquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminInquiryService {

    @Autowired
    AdminInquiryMapper adminInquiryMapper;

    public List<AdminInquiryDto> getAllInquiry() {
        return adminInquiryMapper.findAllInquiry();
    }

    public AdminInquiryDto getInquiryById(int inquiryId) {
        return adminInquiryMapper.findInquiryById(inquiryId);
    }

    public void updateInquiryReply(int inquiryId, int adminNum, String inquiriesA) {
        adminInquiryMapper.updateInquiryReply(inquiryId, adminNum, inquiriesA);
    }

    public void clearInquiryAnswer(int inquiryId) {
        adminInquiryMapper.clearInquiryAnswer(inquiryId);
    }

    public void deleteInquiry(int inquiryId) {
        adminInquiryMapper.deleteInquiry(inquiryId);
    }

}
