package com.uberish.trip.client.payment;

import com.uberish.trip.model.Payment;

public interface PaymentsClient {

    Payment createPayment(Payment payment);
}
