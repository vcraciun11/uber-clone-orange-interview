package com.uberish.user.model.trip;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@JsonApiResource(type = "trip", resourcePath = "trips")
public class Trip {

    @JsonApiId
    private UUID id;

    private String startLocation;

    private String endLocation;

    private Date startTime = new Date();

    private Date endTime;

    private Integer rating;

    private UUID driverId;
}
