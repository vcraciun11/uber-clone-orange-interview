package com.uberish.user.client.trip;

import com.uberish.user.model.trip.Trip;

import java.util.List;
import java.util.UUID;

public interface TripsClient {

    List<Trip> findAllByDriverId(UUID id);
}
