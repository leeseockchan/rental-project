package com.road_friends.rentalcar.controller;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import com.road_friends.rentalcar.dto.PaymentDto;
import com.road_friends.rentalcar.service.FastReservationService;
import com.road_friends.rentalcar.service.PayPalService;
import com.road_friends.rentalcar.service.PaymentService;
import com.road_friends.rentalcar.service.ShortReservationService;
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
  private final ShortReservationService shortReservationService;

  // Map의 선언을 이렇게 변경
  private Map<String, Integer> reservationMap = new HashMap<>();


  public APIPayPalController(PayPalService payPalService, PaymentService paymentService, FastReservationService fastReservationService, ShortReservationService shortReservationService) {
    this.payPalService = payPalService;
    this.paymentService = paymentService;
    this.fastReservationService = fastReservationService;
    this.shortReservationService = shortReservationService;
  }

  @PostMapping("/pay")
  public ResponseEntity<?> payment(@RequestBody Map<String, Object> request) {
    try {
      // 가격 정보, 예약번호 및 예약타입 받기
      Object paymentObj = request.get("payment");
      Object reservationObj = request.get("reservationId");
      Object reservationTypeObj = request.get("reservationType");

      double paymentAmount = 0.0;
      if (paymentObj instanceof Integer) {
        paymentAmount = ((Integer) paymentObj).doubleValue();
      } else if (paymentObj instanceof Double) {
        paymentAmount = (Double) paymentObj;
      }

//      int reservationId = 0;
//      if (reservationObj instanceof Integer) {
//        reservationId = (Integer) reservationObj;
//      }

      // 예약 타입 받기 (fast 또는 short)
      String reservationType = reservationTypeObj instanceof String ? (String) reservationTypeObj : "fast";

      // success URL에 reservationId와 reservationType을 쿼리 파라미터로 추가
      String successUrl = serverUrl + "/api/paypal/success?reservationId=" + reservationObj + "&reservationType=" + reservationType;
//      String successUrl = "http://localhost:3000/myPage/history";
      // PayPal 결제 생성
      String redirectUrl = payPalService.createPayment(paymentAmount, "USD", "paypal", "sale", "Payment Description",
              serverUrl + "/api/paypal/cancel",
              successUrl); // 수정된 successUrl 사용

      // 결제 생성 후 리다이렉션 URL 반환
      return ResponseEntity.ok(Map.of("redirectUrl", redirectUrl));
    } catch (PayPalRESTException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Map.of("error", "Payment creation failed."));
    }
  }

  @GetMapping("/success")
  public ResponseEntity<?> success(@RequestParam("paymentId") String paymentId,
                                   @RequestParam("PayerID") String payerId,
                                   @RequestParam("reservationId") int reservationId,
                                   @RequestParam("reservationType") String reservationType) {
    try {
      Payment payment = payPalService.executePayment(paymentId, payerId);

      // 디버그 출력
      System.out.println("Received reservationId: " + reservationId);  // reservationId 확인
      System.out.println("Received reservationType: " + reservationType);  // reservationType 확인
      System.out.println("paymentId: " + paymentId);
      System.out.println("PayerID: " + payerId);

      // PaymentDto 생성 및 DB 저장
      PaymentDto paymentDto = new PaymentDto();
      paymentDto.setPaymentId(payment.getId());
      paymentDto.setPayerId(payment.getPayer().getPayerInfo().getPayerId());
      paymentDto.setReservationId(reservationId);  // reservationId 설정
      paymentDto.setReservationType(reservationType);  // reservationType 설정
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

      // 예약 상태 업데이트
      if ("fast".equals(reservationType)) {
        fastReservationService.updateRentalStateToConfirmed(reservationId); // fast 예약 처리
      } else if ("short".equals(reservationType)) {
        shortReservationService.updateRentalStateToConfirmed(reservationId); // short 예약 처리
      }

      // React 애플리케이션 결제 완료 페이지로 리디렉션
      String reactSuccessUrl = "http://localhost:3000/myPage/history";

      // 리디렉션을 위한 ResponseEntity 반환
      return ResponseEntity.status(HttpStatus.FOUND)
              .header(HttpHeaders.LOCATION, reactSuccessUrl)  // React 결제 완료 페이지로 리디렉션
              .build();

    } catch (PayPalRESTException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Map.of("error", "Payment execution failed."));
    }
  }
  }
