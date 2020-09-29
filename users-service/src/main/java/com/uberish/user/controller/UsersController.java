package com.uberish.user.controller;

import com.uberish.user.model.Car;
import com.uberish.user.model.entity.User;
import com.uberish.user.model.trip.AverageTrip;
import com.uberish.user.model.trip.Trip;
import com.uberish.user.service.UserAverageTripService;
import com.uberish.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final UserAverageTripService userAverageTripService;

    @Autowired
    public UsersController(UsersService usersService, UserAverageTripService userAverageTripService) {
        this.usersService = usersService;
        this.userAverageTripService = userAverageTripService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(usersService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable UUID id) {
        return ResponseEntity.ok(usersService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        usersService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<List<Car>> findAllCarsByUserId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(usersService.findAllCarsByUserId(id));
    }

    @PostMapping("/{id}/cars")
    public ResponseEntity<Car> createCarForUser(@PathVariable("id") UUID id, @RequestBody Car car) {
        return ResponseEntity.ok(usersService.createCarForUser(id, car));
    }

    @GetMapping("/{id}/trips")
    public ResponseEntity<List<Trip>> findAllTripsByUserId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(usersService.findAllTripsByUserId(id));
    }

    @GetMapping("/{id}/averageTrip")
    public ResponseEntity<AverageTrip> getAverageTripByUserId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userAverageTripService.calculateAverageTripByUserId(id));
    }
}
