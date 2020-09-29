package com.uberish.car.repository;

import com.uberish.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaCarsRepository extends JpaRepository<Car, UUID> {

    @Modifying
    @Query("UPDATE Car c SET c.status = 'IDLE' WHERE c.driverId = :driverId AND c.status = 'ACTIVE'")
    void updateStatusToIdleForAllActiveCarsByDriverId(@Param("driverId") UUID driverId);

}
