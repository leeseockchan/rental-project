package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminInquiryMapper {

    List<AdminInquiryDto> findAllInquiry(@Param("size") int size, @Param("offset") int offset);
    int countTotal();

    AdminInquiryDto findInquiryById(int inquiryId);

    AdminInquiryDto findInquiryReplyById(int inquiryId);

    void updateInquiryReply(@Param("inquiryId") int inquiryId,
                        @Param("adminNum") int adminNum,
                        @Param("inquiriesA") String inquiriesA);

    void clearInquiryAnswer(int inquiryId);

    void deleteInquiry(int inquiryId);

}
