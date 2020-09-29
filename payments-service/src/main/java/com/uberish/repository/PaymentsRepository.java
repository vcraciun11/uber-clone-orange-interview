package com.uberish.repository;

import com.uberish.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findAllByDriverIdAndTripId(UUID driverId, UUID tripId);
}
