package com.uberish.model;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonApiResource(type = "trip", resourcePath = "trips")
public class Trip {

    @JsonApiId
    private UUID id;

    private Date startTime;

    private Date endTime;
}
