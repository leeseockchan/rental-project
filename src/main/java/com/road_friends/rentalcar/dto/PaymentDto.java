package com.road_friends.rentalcar.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Long id;
    private String paymentId;
    private String payerId;
    private Long reservationId;
    private String reservationType; // fast, short
    private BigDecimal amount;
    private String currency; // krw(원화), usd(미국달러)
    private String paymentGateway; // paypal, kakao_pay, naver_pay, apple_pay, stripe
    private String state; // created(생성), approved(승인), failed(샐패), canceled(취소)
    private String status; // success
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType != null ? reservationType.toLowerCase() : null;
    }

    public void setCurrency(String currency) {
        this.currency = currency != null ? currency.toLowerCase() : null;
    }

    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway != null ? paymentGateway.toLowerCase() : null;
    }

    public void setState(String state) {
        this.state = state != null ? state.toLowerCase() : null;
    }

    public void setStatus(String status) {
        this.status = status != null ? state.toLowerCase() : null;
    }

    public Map<String, Object> getResponseMap() {
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("paymentId", paymentId);
        response.put("payerId", payerId);
        response.put("reservationId", reservationId);
        response.put("reservationType", reservationType);
        response.put("amount", amount);
        response.put("currency", currency);
        response.put("paymentGateway", paymentGateway);
        response.put("state", state);
        response.put("createAt", createAt);
        response.put("updatedAt", updatedAt);
        return response;
    }
}
