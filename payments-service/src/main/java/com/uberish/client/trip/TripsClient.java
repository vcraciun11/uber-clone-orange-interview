package com.uberish.client.trip;

import com.uberish.model.Trip;

import java.util.UUID;

public interface TripsClient {

    void update(Trip trip);

    Trip findById(UUID id);
}
