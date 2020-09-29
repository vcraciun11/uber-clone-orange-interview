package com.uberish.service;

import com.uberish.client.trip.TripsClient;
import com.uberish.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TripsClientService {

    private final TripsClient tripsServiceClient;

    @Autowired
    public TripsClientService(TripsClient tripsServiceClient) {
        this.tripsServiceClient = tripsServiceClient;
    }

    public void updateStartTime(Date date, UUID tripId) {
        Trip trip = tripsServiceClient.findById(tripId);
        trip.setStartTime(date);
        tripsServiceClient.update(trip);
    }

    public void updateEndTime(Date date, UUID tripId) {
        Trip trip = tripsServiceClient.findById(tripId);
        trip.setEndTime(date);
        tripsServiceClient.update(trip);
    }

}
