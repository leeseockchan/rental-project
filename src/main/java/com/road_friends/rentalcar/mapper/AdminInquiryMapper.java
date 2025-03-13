package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminInquiryMapper {

    AdminInquiryDto findInquiryById(int inquiryId);

    AdminInquiryDto findInquiryReplyById(int inquiryId);

    void updateInquiryReply(@Param("inquiryId") int inquiryId,
                        @Param("adminNum") int adminNum,
                        @Param("inquiriesA") String inquiriesA);

    void clearInquiryAnswer(int inquiryId);

    void updateInquiryStatus(@Param("inquiryId") int inquiryId, @Param("status") int status);

    long countTotalInquiries();
    long countAnsweredInquiries();
    long countUnansweredInquiries();
    long countActivatedInquiries();
    long countDeactivatedInquiries();

    List<AdminInquiryDto> getInquiries(@Param("offset") int offset, @Param("size") int size);
    List<AdminInquiryDto> getInquiriesByContent(@Param("offset") int offset, @Param("size") int size, @Param("content") String content);
    int getTotalCount();
    int getTotalCountByContent(@Param("content") String content);
}
