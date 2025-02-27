package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.dto.FastReservationDto;
import com.road_friends.rentalcar.service.AdminReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/reservations")
public class AdminReservationController {

  @Autowired
  private AdminReservationService adminReservationService;

  // 예약 목록 조회
  @GetMapping
  public String getAllReservations(Model model) {
    List<FastReservationDto> reservations = adminReservationService.findAllReservations();
    model.addAttribute("reservations", reservations);
    return "reservation_page/reservation_list";
  }

  // 개별 예약 상세 조회
  @GetMapping("/{reservationId}")
  public String getReservationDetail(@PathVariable int reservationId, Model model) {
    FastReservationDto reservation = adminReservationService.findReservationById(reservationId);
    model.addAttribute("reservation", reservation);
    return "reservation_page/reservation_detail";
  }

  // 삭제
  @PostMapping("/{reservationId}/delete")
  public String deleteReservation(@PathVariable("reservationId") int reservationId, RedirectAttributes redirectAttributes) {
    adminReservationService.deleteReservation(reservationId);
    redirectAttributes.addFlashAttribute("message", "예약이 삭제되었습니다.");
    return "redirect:/admin/reservations";
  }

  // 예약 상태 수정
  @PostMapping("/{reservationId}/update-status")
  public String updateReservationStatus(@PathVariable("reservationId") int reservationId,
                                        @RequestParam("rentalState") int rentalState,
                                        RedirectAttributes redirectAttributes) {
    adminReservationService.updateRentalState(reservationId, rentalState);
    redirectAttributes.addFlashAttribute("message", "예약 상태가 변경되었습니다.");
    return "redirect:/admin/reservations/" + reservationId;
  }


}

