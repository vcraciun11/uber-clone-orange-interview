package com.uberish.trip.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class Payment {

    PaymentType paymentType;

    BigDecimal amount;

    UUID tripId;

    UUID driverId;
}
