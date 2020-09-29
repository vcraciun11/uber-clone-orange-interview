package com.uberish.trip.model.entity;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "trips")
@JsonApiResource(type = "trip", resourcePath = "trips")
public class Trip {

    @Id
    @JsonApiId
    @GeneratedValue
    private UUID id;

    private BigDecimal amount;

    private String startLocation;

    private String endLocation;

    private Date startTime;

    private Date endTime;

    private Integer rating;

    private UUID driverId;
}
