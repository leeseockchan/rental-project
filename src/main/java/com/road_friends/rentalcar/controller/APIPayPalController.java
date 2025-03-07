package com.road_friends.rentalcar.controller;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import com.road_friends.rentalcar.dto.PaymentDto;
import com.road_friends.rentalcar.service.PayPalService;
import com.road_friends.rentalcar.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
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

  public APIPayPalController(PayPalService payPalService, PaymentService paymentService) {
    this.payPalService = payPalService;
    this.paymentService = paymentService;
  }

  @PostMapping("/pay")
  public ResponseEntity<?> payment(@RequestBody Map<String, Object> request) {
    try {
      // 가격 정보 받기
      Object paymentObj = request.get("payment");

      // Integer를 Double로 변환 (이 경우 자동으로 변환되지 않음)
      double paymentAmount = 0.0;
      if (paymentObj instanceof Integer) {
        paymentAmount = ((Integer) paymentObj).doubleValue();  // Integer -> Double로 변환
      } else if (paymentObj instanceof Double) {
        paymentAmount = (Double) paymentObj;
      }

      // TODO 예약정보 검증

      String redirectUrl = payPalService.createPayment(paymentAmount, "USD", "paypal",
              "sale", "Payment Description",
              serverUrl + "/api/paypal/cancel",
              serverUrl + "/api/paypal/success");

      // 클라이언트에게 리디렉션 URL을 JSON 응답으로 전달
      return ResponseEntity.ok(Map.of("redirectUrl", redirectUrl));
    } catch (PayPalRESTException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Map.of("error", "Payment creation failed."));
    }
  }

  @GetMapping("/success")
  public ResponseEntity<?> success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
    try {
      Payment payment = payPalService.executePayment(paymentId, payerId);

      // PaymentDto 생성
      PaymentDto paymentDto = new PaymentDto();
      paymentDto.setPaymentId(payment.getId());
      paymentDto.setPayerId(payment.getPayer().getPayerInfo().getPayerId());
      paymentDto.setReservationId(2L);
      paymentDto.setReservationType("fast");
      paymentDto.setPaymentGateway("paypal");
      paymentDto.setState(payment.getState());
      paymentDto.setStatus("success");
      // Payment에서 Amount와 Currency 가져오기
      List<Transaction> transactions = payment.getTransactions();
      if(transactions != null && !transactions.isEmpty()) {
        Amount amount = transactions.get(0).getAmount();
        paymentDto.setAmount(new BigDecimal(amount.getTotal()));
        paymentDto.setCurrency(amount.getCurrency());
      }

      // DB에 저장
      paymentService.createPayment(paymentDto);


      // 응답
      return ResponseEntity.ok().body(paymentDto.getResponseMap());
    } catch (PayPalRESTException e) {
      // TODO 로그 남기기
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Map.of("error", "Payment execution failed."));
    }
  }

  @GetMapping("/cancel")
  public ResponseEntity<?> cancel() {
    return ResponseEntity.ok().body(Map.of("status", "cancel", "message", "Payment was cancelled."));
  }
}
