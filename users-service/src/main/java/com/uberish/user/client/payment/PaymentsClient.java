package com.uberish.user.client.payment;

import com.uberish.user.model.Payment;

import java.util.List;
import java.util.UUID;

public interface PaymentsClient {

    List<Payment> findAllByDriverIdAndTripId(UUID driverId, UUID tripId);
}
