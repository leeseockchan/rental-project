package com.road_friends.rentalcar.controller;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import com.road_friends.rentalcar.dto.PaymentDto;
import com.road_friends.rentalcar.service.FastReservationService;
import com.road_friends.rentalcar.service.PayPalService;
import com.road_friends.rentalcar.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paypal")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class APIPayPalController {

  @Value("${server.url}")
  private String serverUrl;

  private final PayPalService payPalService;
  private final PaymentService paymentService;
  private final FastReservationService fastReservationService;

  // Map의 선언을 이렇게 변경
  private Map<String, Integer> reservationMap = new HashMap<>();


  public APIPayPalController(PayPalService payPalService, PaymentService paymentService, FastReservationService fastReservationService) {
    this.payPalService = payPalService;
    this.paymentService = paymentService;
    this.fastReservationService = fastReservationService;
  }

  @PostMapping("/pay")
  public ResponseEntity<?> payment(@RequestBody Map<String, Object> request) {
    try {
      // 결제 금액과 예약 ID 받기
      Object paymentObj = request.get("payment");
      Object reservationObj = request.get("reservationId");

      double paymentAmount = 0.0;
      if (paymentObj instanceof Integer) {
        paymentAmount = ((Integer) paymentObj).doubleValue();
      } else if (paymentObj instanceof Double) {
        paymentAmount = (Double) paymentObj;
      }

      int reservationId = 0;
      if (reservationObj instanceof Integer) {
        reservationId = (Integer) reservationObj;
      }

      // PayPal 결제 생성, redirectUrl에 reservationId를 쿼리 파라미터로 추가
      String redirectUrl = payPalService.createPayment(paymentAmount, "USD", "paypal",
              "sale", "Payment Description",
              serverUrl + "/api/paypal/cancel",
              serverUrl + "/api/paypal/success?reservationId=" + reservationId);

      // 예약 ID와 결제 ID 매핑
      reservationMap.put(redirectUrl, reservationId);

      // 디버그 로그 추가: reservationMap에 저장된 값 확인
      System.out.println("reservationMap: " + reservationMap);
      System.out.println("Redirect URL: " + redirectUrl); // Redirect URL 확인

      return ResponseEntity.ok(Map.of("redirectUrl", redirectUrl));
    } catch (PayPalRESTException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Map.of("error", "Payment creation failed."));
    }
  }

  @GetMapping("/success")
  public ResponseEntity<?> success(@RequestParam("paymentId") String paymentId,
                                   @RequestParam("PayerID") String payerId,
                                   @RequestParam("reservationId") int reservationId) {  // reservationId를 URL에서 받음
    try {
      Payment payment = payPalService.executePayment(paymentId, payerId);

      // 디버그 출력
      System.out.println("Received reservationId: " + reservationId);  // reservationId 확인
      System.out.println("paymentId: " + paymentId);
      System.out.println("PayerID: " + payerId);

      // PaymentDto 생성 및 DB 저장
      PaymentDto paymentDto = new PaymentDto();
      paymentDto.setPaymentId(payment.getId());
      paymentDto.setPayerId(payment.getPayer().getPayerInfo().getPayerId());
      paymentDto.setReservationId(reservationId);  // reservationId 설정
      paymentDto.setReservationType("fast");
      paymentDto.setPaymentGateway("paypal");
      paymentDto.setState(payment.getState());
      paymentDto.setStatus("success");

      // 결제 금액 및 통화 설정
      List<Transaction> transactions = payment.getTransactions();
      if (transactions != null && !transactions.isEmpty()) {
        Amount amount = transactions.get(0).getAmount();
        paymentDto.setAmount(new BigDecimal(amount.getTotal()));
        paymentDto.setCurrency(amount.getCurrency());
      }

      // DB 저장
      paymentService.createPayment(paymentDto);

      // fast_reservation 테이블의 rental_state를 0에서 1로 업데이트
      fastReservationService.updateRentalStateToConfirmed(reservationId);

      return ResponseEntity.ok().body(paymentDto.getResponseMap());
    } catch (PayPalRESTException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Map.of("error", "Payment execution failed."));
    }
  }
  }
