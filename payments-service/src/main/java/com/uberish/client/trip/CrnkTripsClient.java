package com.uberish.client.trip;

import com.uberish.model.Trip;
import io.crnk.client.CrnkClient;
import io.crnk.core.queryspec.FilterOperator;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.UUID;

@Component
public class CrnkTripsClient implements TripsClient {

    private final ResourceRepository<Trip, UUID> tripsRepository;

    public CrnkTripsClient(@Value("${payments.client.trips-service.crnk.url}") String url) {
        tripsRepository = new CrnkClient(url).getRepositoryForType(Trip.class);
    }

    @Override
    public void update(Trip trip) {
        tripsRepository.save(trip);
    }

    @Override
    public Trip findById(UUID id) {
        QuerySpec querySpec = new QuerySpec(Trip.class);

        FilterSpec filter = new FilterSpec(Collections.singletonList("id"), FilterOperator.EQ, id);
        querySpec.addFilter(filter);

        return tripsRepository.findOne(id, querySpec);
    }
}
