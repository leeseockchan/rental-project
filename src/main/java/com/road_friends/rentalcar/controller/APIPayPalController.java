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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paypal")
public class APIPayPalController {

    @Value("${server.url}")
    private String serverUrl;

    private final PayPalService payPalService;
    private final PaymentService paymentService;

    public APIPayPalController(PayPalService payPalService, PaymentService paymentService) {
        this.payPalService = payPalService;
        this.paymentService = paymentService;
    }

    @GetMapping("/pay")
    public ResponseEntity<?> payment() {
        try {
            // TODO 예약정보 검증

            String redirectUrl = payPalService.createPayment(20.00, "USD", "paypal",
                    "sale", "Payment Description",
                    serverUrl+"/api/paypal/cancel",
                    serverUrl+"/api/paypal/success");
            // 성공 시 redirect, 실패 시 JSON 응답을 반환
            // HttpHeaders를 사용하여 리다이렉트 URL을 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(redirectUrl));
            return ResponseEntity.status(HttpStatus.FOUND)  // 302 Redirect
                    .headers(headers)
                    .build();
        } catch (PayPalRESTException e) {
            // TODO 로그 남기기
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