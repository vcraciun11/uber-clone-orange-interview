package com.uberish.car.service;

import com.uberish.car.model.Car;
import com.uberish.car.model.CarStatus;
import com.uberish.car.repository.JpaCarsRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CarsService extends JpaEntityRepositoryBase<Car, UUID> {

    private final JpaCarsRepository jpaCarsRepository;

    @Autowired
    public CarsService(JpaCarsRepository jpaCarsRepository) {
        super(Car.class);
        this.jpaCarsRepository = jpaCarsRepository;
    }

    @Override
    @Transactional
    public <S extends Car> S create(S resource) {
        jpaCarsRepository.updateStatusToIdleForAllActiveCarsByDriverId(resource.getDriverId());
        return super.create(resource);
    }

    @Override
    public <S extends Car> S save(S resource) {
        Car existingCar = jpaCarsRepository.findById(resource.getId()).orElseThrow(RuntimeException::new);
        if (existingCar.getStatus() == CarStatus.IDLE && resource.getStatus() != existingCar.getStatus()) {
            jpaCarsRepository.updateStatusToIdleForAllActiveCarsByDriverId(resource.getDriverId());
        }
        return super.save(resource);
    }
}
