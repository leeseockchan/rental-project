package com.road_friends.rentalcar.controller;

import com.road_friends.rentalcar.dto.AdminCarDto;
import com.road_friends.rentalcar.dto.AdminShortReservationDto;
import com.road_friends.rentalcar.dto.PageDto;
import com.road_friends.rentalcar.service.AdminShortReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/shortreservations")
public class AdminShortReservationController {

  @Autowired
  private AdminShortReservationService adminShortReservationService;

  // 예약 목록 조회 (페이지네이션 적용)
  @GetMapping
  public String getAllReservations(@RequestParam(name="page", defaultValue = "1") int page,
                                   @RequestParam(name="size", defaultValue = "10") int size,
                                   Model model) {
    PageDto<AdminShortReservationDto> pageDto = adminShortReservationService.findReservationsWithPagination(page, size);
    List<AdminCarDto> maintenanceCars = adminShortReservationService.getMaintenanceCars();

    model.addAttribute("pageDto", pageDto);
    model.addAttribute("reservations", pageDto.getItems()); // ✅ 여기 추가!
    model.addAttribute("maintenanceCars", maintenanceCars);

    return "short-reservation/short-reservation-list";
  }


  // 개별 예약 상세 조회
  @GetMapping("/{reservationId}")
  public String getReservationDetail(@PathVariable int reservationId, Model model) {
    AdminShortReservationDto reservation = adminShortReservationService.findReservationById(reservationId);
    model.addAttribute("reservation", reservation);
    return "short-reservation/short-reservation-detail";
  }

  // 삭제
  @PostMapping("/{reservationId}/delete")
  public String deleteReservation(@PathVariable("reservationId") int reservationId, RedirectAttributes redirectAttributes) {
    adminShortReservationService.deleteReservation(reservationId);
    redirectAttributes.addFlashAttribute("message", "예약이 삭제되었습니다.");
    return "redirect:/admin/shortreservations";
  }

  // 예약 상태 수정
  @PostMapping("/{reservationId}/update-status")
  public String updateReservationStatus(@PathVariable("reservationId") int reservationId,
                                        @RequestParam("rentalState") int rentalState,
                                        RedirectAttributes redirectAttributes) {
    adminShortReservationService.updateRentalState(reservationId, rentalState);
    redirectAttributes.addFlashAttribute("message", "예약 상태가 변경되었습니다.");
    return "redirect:/admin/shortreservations/" + reservationId;
  }

  // 정비 상태 변경
  @PostMapping("/car/{carId}/complete-maintenance")
  public String completeMaintenance(@PathVariable int carId, RedirectAttributes redirectAttributes) {
    adminShortReservationService.markCarAsAvailable(carId);
    redirectAttributes.addFlashAttribute("message", "정비가 완료되었습니다.");
    return "redirect:/admin/shortreservations";
  }

  // 정비 차량 상세 정보 조회
  @GetMapping("/car/{carId}/maintenance-detail")
  public String getMaintenanceCarDetail(@PathVariable int carId, Model model) {
    AdminCarDto car = adminShortReservationService.getMaintenanceCarDetail(carId);
    if (car == null) {
      model.addAttribute("errorMessage", "해당 차량을 찾을 수 없습니다.");
      return "error_page";
    }
    model.addAttribute("car", car);
    return "short-reservation/short-reservation-maintenance";

  }
}

