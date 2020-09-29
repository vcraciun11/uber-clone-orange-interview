package com.uberish.service;

import com.uberish.model.PaymentStatus;
import com.uberish.model.PaymentType;
import com.uberish.model.entity.Payment;
import com.uberish.repository.PaymentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;
    private final TripsClientService tripsClientService;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository, TripsClientService tripsClientService) {
        this.paymentsRepository = paymentsRepository;
        this.tripsClientService = tripsClientService;
    }

    public List<Payment> findAll() {
        return paymentsRepository.findAll();
    }

    public List<Payment> findAllByDriverIdAndTripId(UUID driverId, UUID tripId) {
        return paymentsRepository.findAllByDriverIdAndTripId(driverId, tripId);
    }

    @Transactional
    public void deleteById(UUID id) {
        paymentsRepository.deleteById(id);
    }

    @Async
    public Payment create(Payment payment) {
        paymentsRepository.save(payment);

        try {
            log.debug("Delaying to emulate payment processing time...");
            TimeUnit.SECONDS.sleep(new Random().nextInt((15 - 5) + 5));
        } catch (InterruptedException e) {
            log.warn("Could not add delay...");
        }

        if (payment.getPaymentType() == PaymentType.INITIAL) {
            tripsClientService.updateStartTime(new Date(), payment.getTripId());
        } else {
            tripsClientService.updateEndTime(new Date(), payment.getTripId());
        }

        payment.setConfirmationTime(new Date());
        payment.setPaymentStatus(PaymentStatus.SUCCEEDED);
        return paymentsRepository.save(payment);
    }
}
