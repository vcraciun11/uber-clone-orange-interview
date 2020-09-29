package com.uberish.user.client.trip;

import com.uberish.user.model.trip.Trip;
import io.crnk.client.CrnkClient;
import io.crnk.core.queryspec.FilterOperator;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class CrnkTripsClient implements TripsClient {

    private final ResourceRepository<Trip, UUID> tripsRepository;

    public CrnkTripsClient(@Value("${users.client.trips-service.crnk.url}") String tripsServiceUrl) {
        CrnkClient tripsServiceClient = new CrnkClient(tripsServiceUrl);
        tripsRepository = tripsServiceClient.getRepositoryForType(Trip.class);
    }

    @Override
    public List<Trip> findAllByDriverId(UUID id) {
        QuerySpec querySpec = new QuerySpec(Trip.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("driverId"), FilterOperator.EQ, id));
        return new ArrayList<>(tripsRepository.findAll(querySpec));
    }
}
