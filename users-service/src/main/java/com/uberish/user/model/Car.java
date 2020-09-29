package com.uberish.user.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@JsonApiResource(type = "car", resourcePath = "cars")
public class Car {

    @JsonApiId
    private UUID id;

    private String name;

    private String color;

    private String registrationNumber;

    private Date registrationTimestamp;

    private String status;

    private UUID driverId;
}
