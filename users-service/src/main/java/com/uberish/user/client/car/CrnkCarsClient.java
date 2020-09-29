package com.uberish.user.client.car;

import com.uberish.user.model.Car;
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
public class CrnkCarsClient implements CarsClient {

    private final ResourceRepository<Car, UUID> carsRepository;

    public CrnkCarsClient(@Value("${users.client.cars-service.crnk.url}") String carsServiceUrl) {
        CrnkClient carsServiceClient = new CrnkClient(carsServiceUrl);
        carsRepository = carsServiceClient.getRepositoryForType(Car.class);
    }

    @Override
    public List<Car> findAllByDriverId(UUID id) {
        QuerySpec querySpec = new QuerySpec(Car.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("driverId"), FilterOperator.EQ, id));
        return new ArrayList<>(carsRepository.findAll(querySpec));
    }

    @Override
    public Car create(Car car) {
        return carsRepository.create(car);
    }
}
