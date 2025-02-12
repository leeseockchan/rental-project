package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminInquiryMapper {

    List<AdminInquiryDto> findAllInquiry();

    AdminInquiryDto findInquiryById(int inquiryId);

    void updateInquiryReply(int inquiryId, String inquiriesA);

    void clearInquiryAnswer(int inquiryId);

    void deleteInquiry(int inquiryId);

}
