package com.uberish.user.model.trip;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Duration;

@Data
@AllArgsConstructor
public class AverageTrip {

    private Double averageRating;

    private BigDecimal averageAmount;

    private Duration averageDuration;
}
