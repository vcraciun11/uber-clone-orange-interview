package com.uberish.trip.service;

import com.uberish.trip.model.PaymentType;
import com.uberish.trip.model.entity.Trip;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TripsService extends JpaEntityRepositoryBase<Trip, UUID> {

    private static final BigDecimal HALF = new BigDecimal("0.5");

    private final PaymentsClientService paymentsServiceClient;

    public TripsService(PaymentsClientService paymentsServiceClient) {
        super(Trip.class);
        this.paymentsServiceClient = paymentsServiceClient;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public <S extends Trip> S save(S resource) {
        if (resource.getDriverId() != null) {
            PaymentType paymentType = resource.getEndTime() == null ? PaymentType.INITIAL : PaymentType.FINAL;
            paymentsServiceClient.createPayment(
                    paymentType,
                    resource.getAmount().multiply(HALF),
                    resource.getId(),
                    resource.getDriverId());
        }
        return super.save(resource);
    }
}
