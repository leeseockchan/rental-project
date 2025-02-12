package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import com.road_friends.rentalcar.service.AdminInquiryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/api/admin/inquiry")
public class AdminInquiryController {
    
    @Autowired
    private AdminInquiryService adminInquiryService;

    // 문의 목록 조회 (HTML 렌더링)
    @GetMapping
    public String getAllInquiry(Model model) {
        List<AdminInquiryDto> inquiries = adminInquiryService.getAllInquiry();
        model.addAttribute("inquiries", inquiries);
        return "inquiry/inquiry_list";  // inquiry_list.html로 이동
    }

    // 문의 상세 조회 페이지
    @GetMapping("/{inquiryId}")
    public String getInquiryById(@PathVariable("inquiryId") int inquiryId, Model model) {
        AdminInquiryDto inquiry = adminInquiryService.getInquiryById(inquiryId);
        if (inquiry == null) {
            return "redirect:/api/admin/inquiry";  // 문의 목록으로 리디렉션
        }
        model.addAttribute("inquiry", inquiry);
        return "inquiry/inquiry_list_detail";  // inquiry_list_detail.html로 이동
    }

    // 관리자 답변 등록
    @PostMapping("/{inquiryId}/reply")
    public String updateInquiryReply(@PathVariable("inquiryId") int inquiryId,
                                     @RequestParam("inquiriesA") String inquiriesA,
                                     RedirectAttributes redirectAttributes) {
        // 답변을 등록하는 서비스 호출
        adminInquiryService.updateInquiryReply(inquiryId, inquiriesA);

        // 답변이 등록된 후, 해당 문의 상세 페이지로 리디렉션
        redirectAttributes.addAttribute("inquiryId", inquiryId);
        return "redirect:/api/admin/inquiry/{inquiryId}"; // 문의 상세 페이지로 리디렉션
    }

    // 관리자 답변 삭제
    @PostMapping("/{inquiryId}/reply/clear")
    public String clearInquiryAnswer(@PathVariable("inquiryId") int inquiryId) {
        adminInquiryService.clearInquiryAnswer(inquiryId);
        return "redirect:/api/admin/inquiry";
    }

    // 관리자 문의 삭제
    @PostMapping("/{inquiryId}/delete")
    public String deleteInquiry(@PathVariable("inquiryId") int inquiryId) {
        adminInquiryService.deleteInquiry(inquiryId);
        return "redirect:/api/admin/inquiry";
    }
}
