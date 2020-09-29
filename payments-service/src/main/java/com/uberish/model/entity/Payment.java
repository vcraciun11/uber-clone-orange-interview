package com.uberish.model.entity;

import com.uberish.model.PaymentStatus;
import com.uberish.model.PaymentType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal amount;

    private Date initiationTime = new Date();

    private Date confirmationTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private UUID tripId;

    private UUID driverId;
}