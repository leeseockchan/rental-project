package com.road_friends.rentalcar.mapper;

import com.road_friends.rentalcar.dto.APIInquiriesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface APIInquiriesMapper {

    // 로그인한 사용자의 문의사항 전체 조회
    List<APIInquiriesDto> getUserInquiries(@Param("userNum") Long userNum,
                                           @Param("offset") int offset,
                                           @Param("size") int size);

    // 로그인한 사용자의 문의사항 전체 개수 조회
    int getUserInquiriesCount(@Param("userNum") Long userNum);

    // 로그인한 사용자의 특정 문의사항 상세 조회
    APIInquiriesDto getUserInquiryDetail(Long inquiriesNum);
}
