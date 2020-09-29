package com.uberish.user.service;

import com.uberish.user.client.car.CarsClient;
import com.uberish.user.client.trip.TripsClient;
import com.uberish.user.exception.UserException;
import com.uberish.user.model.Car;
import com.uberish.user.model.entity.User;
import com.uberish.user.model.trip.Trip;
import com.uberish.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final CarsClient carsServiceClient;
    private final TripsClient tripsServiceClient;

    @Autowired
    public UsersService(UsersRepository usersRepository, CarsClient carsServiceClient, TripsClient tripsServiceClient) {
        this.usersRepository = usersRepository;
        this.carsServiceClient = carsServiceClient;
        this.tripsServiceClient = tripsServiceClient;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findById(UUID id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserException("User not found by id: " + id));
    }

    @Transactional
    public User save(User user) {
        return usersRepository.save(user);
    }

    @Transactional
    public User update(User user, UUID id) {
        return usersRepository.findById(id).map(foundUser -> {
            foundUser.setFirstName(user.getFirstName());
            foundUser.setLastName(user.getLastName());
            foundUser.setPhoneNumber(user.getPhoneNumber());
            return save(foundUser);
        }).orElseThrow(() -> new UserException("User not found by id: " + id));
    }

    @Transactional
    public void delete(UUID id) {
        usersRepository.deleteById(id);
    }

    public List<Car> findAllCarsByUserId(UUID id) {
        return carsServiceClient.findAllByDriverId(id);
    }

    public Car createCarForUser(UUID id, Car car) {
        car.setDriverId(id);
        return carsServiceClient.create(car);
    }

    public List<Trip> findAllTripsByUserId(UUID id) {
        return tripsServiceClient.findAllByDriverId(id);
    }
}
