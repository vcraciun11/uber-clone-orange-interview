package com.uberish.trip.service;

import com.uberish.trip.client.payment.PaymentsClient;
import com.uberish.trip.model.Payment;
import com.uberish.trip.model.PaymentType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentsClientService {

    private final PaymentsClient paymentsClient;

    public PaymentsClientService(PaymentsClient paymentsClient) {
        this.paymentsClient = paymentsClient;
    }

    public Payment createPayment(PaymentType type, BigDecimal amount, UUID tripId, UUID driverId) {
        return paymentsClient.createPayment(Payment.builder()
                .paymentType(type)
                .amount(amount)
                .tripId(tripId)
                .driverId(driverId)
                .build());
    }
}
