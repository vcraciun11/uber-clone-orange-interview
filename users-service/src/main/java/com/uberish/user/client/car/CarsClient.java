package com.uberish.user.client.car;

import com.uberish.user.model.Car;

import java.util.List;
import java.util.UUID;

public interface CarsClient {

    List<Car> findAllByDriverId(UUID id);

    Car create(Car car);
}
