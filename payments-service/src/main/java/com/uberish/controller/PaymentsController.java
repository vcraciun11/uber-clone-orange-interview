package com.uberish.controller;

import com.uberish.model.entity.Payment;
import com.uberish.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        return ResponseEntity.ok(paymentsService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAllByDriverIdAndTripId(@RequestParam("driverId") UUID driverID, @RequestParam("tripId") UUID tripId) {
        return ResponseEntity.ok(paymentsService.findAllByDriverIdAndTripId(driverID, tripId));
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment payment) {
        paymentsService.create(payment);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping
    public void delete(@PathVariable UUID id) {
        paymentsService.deleteById(id);
    }
}
