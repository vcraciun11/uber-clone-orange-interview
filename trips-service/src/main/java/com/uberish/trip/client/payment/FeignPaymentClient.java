package com.uberish.trip.client.payment;

import com.uberish.trip.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "payments", url = "${trips.client.payments-service.url}")
public interface FeignPaymentClient extends PaymentsClient {

    @PostMapping
    Payment createPayment(@RequestBody Payment payment);
}
