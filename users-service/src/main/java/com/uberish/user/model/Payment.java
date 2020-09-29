package com.uberish.user.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class Payment {

    private UUID id;

    private BigDecimal amount;

    private Date initiationTime;

    private Date confirmationTime;

    private UUID tripId;

    private UUID driverId;
}
