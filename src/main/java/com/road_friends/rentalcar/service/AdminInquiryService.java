package com.road_friends.rentalcar.service;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.mapper.AdminInquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminInquiryService {

    @Autowired
    AdminInquiryMapper adminInquiryMapper;

    public PageDto getAllInquiry(int page, int size) {
        int offset = (page - 1) * size;
        List<AdminInquiryDto> items = adminInquiryMapper.findAllInquiry(size, offset);
        int totalElements = adminInquiryMapper.countTotal();
        return new PageDto(page, size, totalElements, items);
    }

    public AdminInquiryDto getInquiryById(int inquiryId) {
        return adminInquiryMapper.findInquiryById(inquiryId);
    }

    public AdminInquiryDto getInquiryReplyById(int inquiryId) {
        return adminInquiryMapper.findInquiryReplyById(inquiryId);
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
