package com.uberish.user.client.payment;

import com.uberish.user.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "payments", url = "${users.client.payments-service.url}")
public interface FeignPaymentsClient extends PaymentsClient {

    @GetMapping
    List<Payment> findAllByDriverIdAndTripId(@RequestParam("driverId") UUID driverId, @RequestParam("tripId") UUID tripId);
}
