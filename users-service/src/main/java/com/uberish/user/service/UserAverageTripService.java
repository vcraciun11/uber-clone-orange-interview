package com.uberish.user.service;

import com.uberish.user.client.payment.PaymentsClient;
import com.uberish.user.client.trip.TripsClient;
import com.uberish.user.model.Payment;
import com.uberish.user.model.trip.AverageTrip;
import com.uberish.user.model.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserAverageTripService {

    private final TripsClient tripsServiceClient;
    private final PaymentsClient paymentsServiceClient;

    @Autowired
    public UserAverageTripService(TripsClient tripsServiceClient, PaymentsClient paymentsServiceClient) {
        this.tripsServiceClient = tripsServiceClient;
        this.paymentsServiceClient = paymentsServiceClient;
    }

    public AverageTrip calculateAverageTripByUserId(UUID userId) {
        List<Trip> trips = tripsServiceClient.findAllByDriverId(userId);

        double averageMillis = trips.stream()
                .filter(trip -> trip.getEndTime() != null)
                .map(trip -> Duration.between(trip.getStartTime().toInstant(), trip.getEndTime().toInstant()))
                .mapToLong(Duration::toMillis).average().getAsDouble();
        Duration averageDuration = Duration.of((long) averageMillis, ChronoUnit.MILLIS);

        BigDecimal averageAmount = trips.stream()
                .filter(trip -> trip.getEndTime() != null)
                .map(Trip::getId)
                .map(tripId -> paymentsServiceClient.findAllByDriverIdAndTripId(userId, tripId))
                .collect(Collectors.toList()).stream().flatMap(Collection::stream)
                .collect(Collectors.toMap(Payment::getTripId, Payment::getAmount, BigDecimal::add, HashMap::new))
                .values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        Double averageRating = trips.stream().map(Trip::getRating).mapToInt(rating -> rating).average().getAsDouble();

        return new AverageTrip(averageRating, averageAmount, averageDuration);
    }
}
