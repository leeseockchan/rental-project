package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.AdminInquiryDto;
import com.road_friends.rentalcar.service.AdminInquiryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    // 문의 상세 조회
    @GetMapping("/{inquiryId}")
    public String getInquiryById(@PathVariable("inquiryId") int inquiryId, Model model) {
        AdminInquiryDto inquiry = adminInquiryService.getInquiryById(inquiryId);
        if (inquiry == null) {
            return "redirect:/api/admin/inquiry";
        }
        model.addAttribute("inquiry", inquiry);
        return "inquiry/inquiry_reply";
    }

    // 관리자 답변 등록
    @PostMapping("/{inquiryId}/reply")
    public String updateInquiryReply(
            @PathVariable("inquiryId") int inquiryId,
            @RequestParam("adminNum") int adminNum,
            @RequestParam("inquiriesA") String inquiriesA,
            RedirectAttributes redirectAttributes) {

        adminInquiryService.updateInquiryReply(inquiryId, adminNum, inquiriesA);
        redirectAttributes.addFlashAttribute("successMessage", "답변 등록 성공");  // 성공 메시지 추가
        return "redirect:/api/admin/inquiry/" + inquiryId;  // 해당 문의 상세 페이지로 리디렉션
    }
//    @PostMapping("/{inquiryId}/reply")
//    public String updateInquiryReply(
//            @PathVariable("inquiryId") int inquiryId,
//            @Validated @ModelAttribute AdminInquiryDto adminInquiryDto,  // 유효성 검사 적용
//            BindingResult result,  // 유효성 검사 결과
//            Model model) {
//
//        // 유효성 검사 실패 시
//        if (result.hasErrors()) {
//            // 유효성 검사 실패 메시지 처리 (검사된 필드의 오류)
//            model.addAttribute("errorMessage", "입력값이 잘못되었습니다.");
//            return "redirect:/api/admin/inquiry/" + inquiryId; // 유효성 검사 오류 페이지로 리디렉션
//        }
//
//        // 유효성 검사 통과 시
//        adminInquiryService.updateInquiryReply(inquiryId, adminInquiryDto);
//        model.addAttribute("successMessage", "답변 등록 성공");
//
//        // 해당 문의 상세 페이지로 리디렉션
//        return "redirect:/api/admin/inquiry/" + inquiryId;
//    }

    // 관리자 답변 삭제
    @PostMapping("/{inquiryId}/reply/clear")
    public String clearInquiryAnswer(@PathVariable("inquiryId") int inquiryId,
                                     RedirectAttributes redirectAttributes) {
        adminInquiryService.clearInquiryAnswer(inquiryId);
        redirectAttributes.addFlashAttribute("successMessage", "답변이 삭제되었습니다.");
        return "redirect:/api/admin/inquiry";
    }

    // 관리자 문의 게시글 삭제
    @PostMapping("/{inquiryId}/delete")
    public String deleteInquiry(@PathVariable("inquiryId") int inquiryId,
                                RedirectAttributes redirectAttributes) {
        adminInquiryService.deleteInquiry(inquiryId);
        redirectAttributes.addFlashAttribute("successMessage", "문의가 삭제되었습니다.");
        return "redirect:/api/admin/inquiry";
    }
}
