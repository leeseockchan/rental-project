package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.service.AdminInquiryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/inquiry")
public class AdminInquiryController {
    
    @Autowired
    private AdminInquiryService adminInquiryService;

    // 문의 목록 조회 (HTML 렌더링)
    @GetMapping
    public String getAllInquiry(@RequestParam(name="page", defaultValue = "1") int page,
                                @RequestParam(name="size", defaultValue = "10") int size,
                                @RequestParam(name="content", required = false) String content,
                                Model model) {
        PageDto<AdminInquiryDto> pageDto = adminInquiryService.getAllInquiry(page, size, content);
        AdminInquiryDto counts = adminInquiryService.getInquiryCounts();  // 통계 데이터

        model.addAttribute("pageDto", pageDto);
        model.addAttribute("counts", counts);  // 통계 데이터를 뷰로 전달
        model.addAttribute("content", content);  // 검색어 전달

        return "inquiry/inquiry-list";  // inquiry_list.html로 이동
    }

    // 문의 상세 조회
    @GetMapping("/{inquiryId}")
    public String getInquiryById(@PathVariable("inquiryId") int inquiryId, Model model) {
        AdminInquiryDto inquiry = adminInquiryService.getInquiryById(inquiryId);
        if (inquiry == null) {
            return "redirect:/admin/inquiry";
        }
        model.addAttribute("inquiry", inquiry);
        return "inquiry/inquiry-detail";
    }

    // 관리자 답변 입력
    @GetMapping("/{inquiryId}/reply")
    public String getInquiryReplyById(@PathVariable("inquiryId") int inquiryId, Model model) {
        AdminInquiryDto inquiry = adminInquiryService.getInquiryReplyById(inquiryId);
        model.addAttribute("inquiry", inquiry);
        return "inquiry/inquiry-reply";
    }

    // 관리자 답변 등록
    @PostMapping("/{inquiryId}/reply")
    public String updateInquiryReply(
            @PathVariable("inquiryId") int inquiryId,
            @RequestParam("inquiriesA") String inquiriesA,
            RedirectAttributes redirectAttributes) {

        adminInquiryService.updateInquiryReply(inquiryId, inquiriesA);
        redirectAttributes.addFlashAttribute("successMessage", "답변을 등록하였습니다.");  // 성공 메시지 추가
        return "redirect:/admin/inquiry/" + inquiryId;  // 해당 문의 상세 페이지로 리디렉션
    }

    // 관리자 답변 삭제
    @PostMapping("/{inquiryId}/reply/clear")
    public String clearInquiryAnswer(@PathVariable("inquiryId") int inquiryId,
                                     RedirectAttributes redirectAttributes) {
        adminInquiryService.clearInquiryAnswer(inquiryId);
        redirectAttributes.addFlashAttribute("successMessage", "답변이 삭제되었습니다.");
        return "redirect:/admin/inquiry";
    }

    // 문의 비활성화
    @PostMapping("/{inquiryId}/deactivate")
    public String deactivateInquiry(@PathVariable("inquiryId") int inquiryId, @RequestParam("status") int status) {
        adminInquiryService.updateInquiryStatus(inquiryId, status);  // inquiries_status를 0으로 설정
        return "redirect:/admin/inquiry/" + inquiryId;  // inquiryId를 경로에 직접 넣어 리다이렉트
    }

    // 문의 활성화
    @PostMapping("/{inquiryId}/activate")
    public String activateInquiry(@PathVariable("inquiryId") int inquiryId, @RequestParam("status") int status) {
        adminInquiryService.updateInquiryStatus(inquiryId, status);  // inquiries_status를 1로 설정
        return "redirect:/admin/inquiry/" + inquiryId;  // inquiryId를 경로에 직접 넣어 리다이렉트
    }

}
