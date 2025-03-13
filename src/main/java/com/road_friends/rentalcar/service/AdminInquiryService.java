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

    public PageDto<AdminInquiryDto> getAllInquiry(int page, int size, String content) {
        int offset = (page - 1) * size;

        // 검색어가 있으면 검색된 문의사항, 없으면 전체 문의사항 조회
        List<AdminInquiryDto> items;
        int totalCount;

        if (content != null && !content.isEmpty()) {
            items = adminInquiryMapper.getInquiriesByContent(offset, size, content);
            totalCount = adminInquiryMapper.getTotalCountByContent(content);
        } else {
            items = adminInquiryMapper.getInquiries(offset, size);
            totalCount = adminInquiryMapper.getTotalCount();
        }

        return new PageDto<>(page, size, totalCount, items);
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

    public void updateInquiryStatus(int inquiryId, int status) {
        adminInquiryMapper.updateInquiryStatus(inquiryId, status);
    }

    public AdminInquiryDto getInquiryCounts() {
        long total = adminInquiryMapper.countTotalInquiries();
        long answered = adminInquiryMapper.countAnsweredInquiries();
        long unanswered = adminInquiryMapper.countUnansweredInquiries();
        long deactivated = adminInquiryMapper.countDeactivatedInquiries();
        long activated = adminInquiryMapper.countActivatedInquiries();

        return new AdminInquiryDto(total, answered, unanswered, activated, deactivated);
    }
}
