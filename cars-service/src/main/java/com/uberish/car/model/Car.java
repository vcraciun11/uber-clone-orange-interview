package com.uberish.car.model;

import io.crnk.core.resource.annotations.JsonApiField;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "cars")
@JsonApiResource(type = "car", resourcePath = "cars")
public class Car {

    @Id
    @JsonApiId
    @GeneratedValue
    private UUID id;

    private String name;

    private String color;

    private String registrationNumber;

    @JsonApiField(postable = false, patchable = false, deletable = false)
    private Date registrationTimestamp = new Date();

    @Enumerated(value = EnumType.STRING)
    @JsonApiField(postable = false, patchable = false, deletable = false)
    private CarStatus status = CarStatus.ACTIVE;

    private UUID driverId;
}
